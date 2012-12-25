package de.futjikato.mrwhiz.xml.attributes;

import de.futjikato.mrwhiz.game.GameRenderer;
import de.futjikato.mrwhiz.xml.XmlObject;

public class Dimensions extends XmlAttribute {

	private int x;

	private int y;

	private int w;

	private int h;

	@Override
	public void handleValue(String value, XmlObject xmlObject) {
		// value can be "10,10,10,10"
		String[] parts = value.split(",");

		if (parts.length == 4) {
			this.h = Integer.parseInt(parts[3]);
		}

		if (parts.length >= 3) {
			this.w = Integer.parseInt(parts[2]);
		}

		if (parts.length >= 2) {
			this.y = Integer.parseInt(parts[1]);
		}

		if (parts.length >= 1) {
			this.x = Integer.parseInt(parts[0]);
		}
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public float getAbsX() {
		return (float) x * GameRenderer.getInstance().getBlocksize();
	}

	public float getAbsY() {
		return y * GameRenderer.getInstance().getBlocksize();
	}

	public int getW() {
		return w;
	}

	public int getH() {
		return h;
	}

	public void moveByDimension(Dimensions targetMove) {
		int newX = this.getX() + targetMove.getX();
		int newY = this.getY() + targetMove.getY();
		int newW = this.getW() + targetMove.getW();
		int newH = this.getH() + targetMove.getH();

		x = newX;
		y = newY;
		w = newW;
		h = newH;
	}

	public String toString() {
		return String.format("xywh( %d, %d, %d, %d )", getX(), getY(), getW(), getH());
	}

	@Override
	public void copyFrom(XmlAttribute o) throws AttributeCopyError {

		if (!(o instanceof Dimensions)) {
			throw new AttributeCopyError();
		}
		Dimensions oDim = (Dimensions) o;

		x = oDim.x;
		y = oDim.y;
		w = oDim.w;
		h = oDim.h;
	}
}
