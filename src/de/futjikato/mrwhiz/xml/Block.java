package de.futjikato.mrwhiz.xml;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import de.futjikato.mrwhiz.xml.attributes.Damage;
import de.futjikato.mrwhiz.xml.attributes.Repeat;
import de.futjikato.mrwhiz.xml.attributes.Speed;
import de.futjikato.mrwhiz.xml.attributes.XmlAttribute;

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

	@Deprecated
	public void draw(int blocksize) {
		this.draw(0, 0, blocksize);
	}

	public void draw(float vpx, float vpy, int blocksize) {
		Image img = this.texture.getImage();
		Graphics gra = new Graphics();

		int abX = (this.getDimensions().getX() * blocksize) - (int) vpx;
		int abY = (this.getDimensions().getY() * blocksize) - (int) vpy;
		int abW = this.getDimensions().getW() * blocksize;
		int abH = this.getDimensions().getH() * blocksize;

		Repeat repeatAttr = this.getRepeat();

		if (repeatAttr == null) {
			gra.drawImage(img, abX, abY, abX + abW, abY + abH, 0, 0, img.getWidth(), img.getHeight());
		} else {
			if (repeatAttr.isRepeatX() && repeatAttr.isRepeatY()) {
				gra.fillRect(abX, abY, abW, abH, img, 0, 0);
			} else if (repeatAttr.isRepeatX()) {
				gra.fillRect(abX, abY, abW, abH, img, 0, 0);
			} else if (repeatAttr.isRepeatY()) {
				gra.fillRect(abX, abY, abW, abH, img, 0, 0);
			}
		}
	}

	private Repeat getRepeat() {
		XmlAttribute xmlAttr = this.getAttribute("repeat");
		if (xmlAttr instanceof Repeat) {
			return (Repeat) xmlAttr;
		}
		return null;
	}

	public int getDamage() {
		XmlAttribute xmlAttr = this.getAttribute("damage");
		if (xmlAttr instanceof Damage) {
			Damage dmg = (Damage) xmlAttr;
			return dmg.getDemaga();
		}
		return 0;
	}

	public float getSpeed() {
		XmlAttribute xmlAttr = this.getAttribute("speed");
		if (xmlAttr instanceof Speed) {
			Speed dmg = (Speed) xmlAttr;
			return dmg.getSpeed();
		}
		return 0.0f;
	}
}
