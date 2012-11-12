package de.futjikato.mrwhiz.xml;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.xml.sax.Attributes;

import de.futjikato.mrwhiz.ObjectConfigs;
import de.futjikato.mrwhiz.Renderable;

public class TextureArea extends XmlObject implements Renderable {

	private Texture texture;
	
	@Override
	public void handleAttributes(Attributes attributes) {
		ObjectConfigs conf = new ObjectConfigs();
		this.conf = conf;
	}

	@Override
	public void handleValue(String currentValue) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addChildObj(XmlObject mapObj) {
		if(mapObj instanceof Texture) {
			this.texture = (Texture) mapObj;
		}
	}

	@Override
	public void draw() {
		GL11.glPushMatrix();
	    
			//TODO calculate position on screen
			GL11.glTranslatef(this.conf.getX(), this.conf.getY(), 0);
			
			//TODO implement fallback color as attribute
			//GL11.glColor3f(this.bgR, this.bgG, this.bgB);
			
			if(this.texture != null) {
				this.texture.bind();  
			}
			
			GL11.glBegin(GL11.GL_QUADS);
				
				GL11.glTexCoord2f(0, 0);
				GL11.glVertex2i(0, 0);
				
				GL11.glTexCoord2f(this.texture.getHorizotalScale(), 0);
				GL11.glVertex2i(this.conf.getWidth(), 0);
				
				GL11.glTexCoord2f(this.texture.getHorizotalScale(), this.texture.getVerticalScale());
				GL11.glVertex2i(this.conf.getWidth(), this.conf.getHeight());
				
				GL11.glTexCoord2f(0, this.texture.getVerticalScale());
				GL11.glVertex2i(0, this.conf.getHeight());
				
			GL11.glEnd();
		 
			// free texture
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
			
		GL11.glPopMatrix();
	}

}
