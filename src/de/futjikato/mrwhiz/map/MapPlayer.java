package de.futjikato.mrwhiz.map;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import de.futjikato.mrwhiz.Renderable;

public final class MapPlayer implements Renderable {
	
	private float x;
	private float y;
	
	private final static int PLAYER_ORIENTATION_UP = 0;
	private final static int PLAYER_ORIENTATION_DOWN = 1;
	private final static int PLAYER_ORIENTATION_LEFT = 2;
	private final static int PLAYER_ORIENTATION_RIGHT = 3;
	
	private int currentOrientation = MapPlayer.PLAYER_ORIENTATION_DOWN;
	
	/**
	 * Index of animation frame
	 */
	private int animStep = 0;
	
	private boolean isStill = true;
	
	private SpriteSheet glSprite;
	
	public MapPlayer(float spawnX, float spawnY) {
		this.setPosition(spawnX, spawnY);
	}
	
	public void setPosition(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	private SpriteSheet getSprite() {
		if(this.glSprite == null) {
			try {
				this.glSprite = new SpriteSheet("resources/images/player_small.png", 20, 37, 0);
			} catch (SlickException e) {
				e.printStackTrace();
			}
		}
		
		return this.glSprite;
	}
	
	private int getSpriteIndex() {
		switch(this.currentOrientation) {
		
			case MapPlayer.PLAYER_ORIENTATION_DOWN:
				return (this.isStill) ? 1 : ( 1 + this.animStep++);
				
			case MapPlayer.PLAYER_ORIENTATION_UP:
				return (this.isStill) ? 0 : ( 0 + this.animStep++);
				
			case MapPlayer.PLAYER_ORIENTATION_LEFT:
				return (this.isStill) ? 7 : ( 7 + this.animStep++);
				
			case MapPlayer.PLAYER_ORIENTATION_RIGHT:
				return (this.isStill) ? 6 : ( 6 + this.animStep++);
		}
		
		// return default as fallback
		return 1;
	}
	
	@Override
	public void draw() {
		GL11.glPushMatrix();
		
		int spriteIndex = this.getSpriteIndex();
		
		SpriteSheet sprite = this.getSprite();
		Image tile = sprite.getSprite(0, spriteIndex);
		
		tile.draw(this.x, this.y);
	}
	
	public void handleInput(long delta, Input input) {
		// move upwards
		if(input.isKeyDown(Input.KEY_W) && !input.isKeyDown(Input.KEY_S)) {
			this.y -= 0.1f * delta;
			this.currentOrientation = MapPlayer.PLAYER_ORIENTATION_UP;
		}
		
		// move downwards
		if(input.isKeyDown(Input.KEY_S) && !input.isKeyDown(Input.KEY_W)) {
			this.y += 0.1f * delta;
			this.currentOrientation = MapPlayer.PLAYER_ORIENTATION_DOWN;
		}
		
		// move left
		if(input.isKeyDown(Input.KEY_A) && !input.isKeyDown(Input.KEY_D)) {
			this.x -= 0.1f * delta;
			this.currentOrientation = MapPlayer.PLAYER_ORIENTATION_LEFT;
		}
		
		// move right
		if(input.isKeyDown(Input.KEY_D) && !input.isKeyDown(Input.KEY_A)) {
			this.x += 0.1f * delta;
			this.currentOrientation = MapPlayer.PLAYER_ORIENTATION_RIGHT;
		}
	}
}
