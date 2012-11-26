package de.futjikato.mrwhiz.xml;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class Block extends XmlObject {

	private Texture texture;

	@Override
	public void handleValue(String currentValue) throws ObjectNoValueSupport {
		throw new ObjectNoValueSupport();
	}

	@Override
	public void addChildObj(XmlObject mapObj) throws ObjectNoChildSupport, ObjectInvalidChild {
		if (mapObj instanceof Texture) {
			this.texture = (Texture) mapObj;
		}
	}

	public void draw(int blocksize) {
		Image img = this.texture.getImage();
		Graphics gra = new Graphics();

		int abX = this.getDimensions().getX() * blocksize;
		int abY = this.getDimensions().getY() * blocksize;

		gra.drawImage(img, abX, abY);
	}
}
