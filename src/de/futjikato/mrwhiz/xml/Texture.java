package de.futjikato.mrwhiz.xml;

import java.io.IOException;

import org.xml.sax.Attributes;

import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class Texture extends XmlObject {

	private  org.newdawn.slick.opengl.Texture glTexture;
	
	@Override
	public void handleAttributes(Attributes attributes) {
		//TODO handle xywh attribute
		
		//TODO handle scale attribute
	}

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
		throw new ObjectNoChildSupport("Texture node does not support any further children.");
	}

	public void bind() {
		if(this.glTexture != null) {
			this.glTexture.bind();
		}
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
