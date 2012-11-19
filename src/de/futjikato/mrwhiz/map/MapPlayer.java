package de.futjikato.mrwhiz.map;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import de.futjikato.mrwhiz.App;
import de.futjikato.mrwhiz.Renderable;
import de.futjikato.mrwhiz.xml.TextureArea;
import de.futjikato.mrwhiz.xml.TextureAreaCollector;

public final class MapPlayer implements Renderable {
	
	private float x;
	private float y;
	
	private final static int PLAYER_HEIGHT = 32;
	private final static int PLAYER_WIDTH = 20;
	
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
	
	public int getXBlock() {
		return Math.round(this.x / 50);
	}
	
	public int getYBlock() {
		return Math.round(this.y / 50);
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
		
		//TODO this could eventually be improved a bit ;-)
		tile.draw(this.x - (MapPlayer.PLAYER_WIDTH / 2), this.y - MapPlayer.PLAYER_HEIGHT);
		
		if(App.getInstance().isDebug()) {
			// draw white bounding box around player if debug
			Graphics graph = new Graphics();
			Color.white.bind();
			graph.fillRect(this.x - (MapPlayer.PLAYER_WIDTH / 2), this.y - MapPlayer.PLAYER_HEIGHT, MapPlayer.PLAYER_WIDTH, MapPlayer.PLAYER_HEIGHT);
		}
	}
	
	public void handleInput(long delta, Input input) {
		float y = this.y;
		float x = this.x;
		
		// move upwards
		if(input.isKeyDown(Input.KEY_W) && !input.isKeyDown(Input.KEY_S)) {
			y -= 0.1f * delta;
			this.currentOrientation = MapPlayer.PLAYER_ORIENTATION_UP;
		}
		
		// move downwards
		if(input.isKeyDown(Input.KEY_S) && !input.isKeyDown(Input.KEY_W)) {
			y += 0.1f * delta;
			this.currentOrientation = MapPlayer.PLAYER_ORIENTATION_DOWN;
		}
		
		// move left
		if(input.isKeyDown(Input.KEY_A) && !input.isKeyDown(Input.KEY_D)) {
			x -= 0.1f * delta;
			this.currentOrientation = MapPlayer.PLAYER_ORIENTATION_LEFT;
		}
		
		// move right
		if(input.isKeyDown(Input.KEY_D) && !input.isKeyDown(Input.KEY_A)) {
			x += 0.1f * delta;
			this.currentOrientation = MapPlayer.PLAYER_ORIENTATION_RIGHT;
		}
		
		if(x != this.x || y != this.y) {
			int xb = (int) x / 50;
			int yb = (int) y / 50;
			
			TextureAreaCollector areaCollector = TextureAreaCollector.getInstance();
			TextureArea currentArea = areaCollector.getArea(xb, yb);
			
			if(currentArea != null && currentArea.isWalkable()) {
				this.x = x;
				this.y = y;
			}
		}
	}
}
