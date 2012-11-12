package de.futjikato.mrwhiz.xml;

import org.xml.sax.Attributes;

import org.newdawn.slick.opengl.TextureLoader;

public class Texture extends XmlObject {

	private  org.newdawn.slick.opengl.Texture glTexture;
	
	@Override
	public void handleAttributes(Attributes attributes) {
		// TODO Auto-generated method stub

	}

	@Override
	public void handleValue(String currentValue) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addChildObj(XmlObject mapObj) throws ObjectNoChildSupport {
		throw new ObjectNoChildSupport("Texture node does not support any further children.");
	}

	public void bind() {
		this.glTexture.bind();
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
