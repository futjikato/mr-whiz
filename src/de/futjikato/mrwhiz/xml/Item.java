package de.futjikato.mrwhiz.xml;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import de.futjikato.mrwhiz.xml.attributes.Dimensions;
import de.futjikato.mrwhiz.xml.attributes.Score;
import de.futjikato.mrwhiz.xml.attributes.XmlAttribute;

public class Item extends XmlObject {

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

	public void draw(float vpx, float vpy, int blocksize) {

		if (this.texture == null) {
			return;
		}

		Dimensions dim = this.getDimensions();

		if (dim != null) {
			Image img = this.texture.getImage();
			Graphics gra = new Graphics();

			int abX = (dim.getX() * blocksize) - (int) vpx;
			int abY = (dim.getY() * blocksize) - (int) vpy;
			int abW = dim.getW() * blocksize;
			int abH = dim.getH() * blocksize;

			gra.drawImage(img, abX, abY, abX + abW, abY + abH, 0, 0, img.getWidth(), img.getHeight());
		}
	}

	public int getScore() {
		XmlAttribute xmlAttr = this.getAttribute("score");
		if (xmlAttr instanceof Score) {
			Score scoreAttr = (Score) xmlAttr;
			return scoreAttr.getValue();
		}

		return 0;
	}
}
