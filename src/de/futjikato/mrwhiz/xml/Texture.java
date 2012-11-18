package de.futjikato.mrwhiz.xml;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Texture extends XmlObject {

	private Image image;

	@Override
	public void handleValue(String currentValue) {
		try {
			this.image = new Image(currentValue);
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void addChildObj(XmlObject mapObj) throws ObjectNoChildSupport {
		throw new ObjectNoChildSupport();
	}
	
	public Image getImage() {
		return this.image;
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
