package de.futjikato.mrwhiz.xml;

import java.io.IOException;

import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class Texture extends XmlObject {

	private  org.newdawn.slick.opengl.Texture glTexture;

	@Override
	public void handleValue(String currentValue) {
		try {
			this.glTexture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(currentValue));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void addChildObj(XmlObject mapObj) throws ObjectNoChildSupport {
		throw new ObjectNoChildSupport();
	}

	public void bind() {
		this.glTexture.bind();
	}
	
	public float getHeight() {
		return this.glTexture.getTextureHeight();
	}

	public float getWidth() {
		return this.glTexture.getTextureWidth();
	}

	public float getHorizotalScale() {
		// TODO Auto-generated method stub
		return 1;
	}

	public float getVerticalScale() {
		// TODO Auto-generated method stub
		return 1;
	}

}
