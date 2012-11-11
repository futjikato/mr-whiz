package de.futjikato.mrwhiz.game.objects;

import java.io.IOException;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class Background implements Renderable {
	
	private Texture texture;
	
	private int bgR;
	private int bgG;
	private int bgB;
	
	public Background() {
		
		// set background color
		this.bgB = 0;
		this.bgG = 0;
		this.bgR = 0;
		
		// try to load background
		try {
			this.texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("resources/images/backgrounds/dev.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void draw() {
		GL11.glPushMatrix();
			    
			GL11.glTranslatef(0, 0, 0);
			GL11.glColor3f(this.bgR, this.bgG, this.bgB);
			
			if(this.texture != null) {
				this.texture.bind();  
			}
			
			GL11.glBegin(GL11.GL_QUADS);
				
				GL11.glTexCoord2f(0, 0);
				GL11.glVertex2i(0, 0);
				
				GL11.glTexCoord2f(10, 0);
				GL11.glVertex2i(Display.getWidth(), 0);
				
				GL11.glTexCoord2f(10, 10);
				GL11.glVertex2i(Display.getWidth(), Display.getHeight());
				
				GL11.glTexCoord2f(0, 10);
				GL11.glVertex2i(0, Display.getHeight());
				
			GL11.glEnd();
		 
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
			
		GL11.glPopMatrix();
		GL11.glFlush();
	}
}
