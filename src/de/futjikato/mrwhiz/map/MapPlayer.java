package de.futjikato.mrwhiz.map;

import java.io.IOException;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Input;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import de.futjikato.mrwhiz.Renderable;

public final class MapPlayer implements Renderable {
	
	private float x;
	private float y;
	
	/**
	 * 0 = Looking up still
	 * 1 = Looking down still
	 * 6 = Looking left still
	 * 7 = Looking right still
	 */
	private int orientation = 0; 
	
	private Texture glTexture;
	
	public MapPlayer(float spawnX, float spawnY) {
		this.setPosition(spawnX, spawnY);
	}
	
	public void setPosition(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	private Texture getTexture() {
		if(this.glTexture == null) {
			try {
				this.glTexture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("resources/images/player_small.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return this.glTexture;
	}
	
	@Override
	public void draw() {
		GL11.glPushMatrix();
	    
		Texture playerTexture = this.getTexture();
		playerTexture.bind();
		
		//TODO calculate position on screen
		GL11.glTranslatef(this.x, this.y, 0);
		
		GL11.glBegin(GL11.GL_QUADS);
			
			GL11.glTexCoord2f(0, (0.07f * this.orientation + 0.07f));
			GL11.glVertex2i(0, 0);
			
			GL11.glTexCoord2f(0.8f, (0.07f * this.orientation + 0.07f));
			GL11.glVertex2i(20, 0);
			
			GL11.glTexCoord2f(0.8f, (0.07f * this.orientation));
			GL11.glVertex2i(20, 37);
			
			GL11.glTexCoord2f(0, (0.07f * this.orientation));
			GL11.glVertex2i(0, 37);
			
		GL11.glEnd();
		
		GL11.glPopMatrix();
	}
	
	public void handleInput(long delta, Input input) {
		// move upwards
		if(input.isKeyDown(Input.KEY_W) && !input.isKeyDown(Input.KEY_S)) {
			this.y += 0.1f * delta;
			this.orientation = 0;
		}
		
		// move downwards
		if(input.isKeyDown(Input.KEY_S) && !input.isKeyDown(Input.KEY_W)) {
			this.y -= 0.1f * delta;
			this.orientation = 1;
		}
		
		// move left
		if(input.isKeyDown(Input.KEY_A) && !input.isKeyDown(Input.KEY_D)) {
			this.x -= 0.1f * delta;
			this.orientation = 6;
		}
		
		// move right
		if(input.isKeyDown(Input.KEY_D) && !input.isKeyDown(Input.KEY_A)) {
			this.x += 0.1f * delta;
			this.orientation = 7;
		}
	}
}
