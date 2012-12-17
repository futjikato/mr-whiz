package de.futjikato.mrwhiz.xml.attributes;

import de.futjikato.mrwhiz.xml.XmlObject;

public class Dimensions extends XmlAttribute {

	private int x = 0;
	private int y = 0;
	private int w = 100;
	private int h = 100;

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

	public int getW() {
		return w;
	}

	public int getH() {
		return h;
	}

	public void moveByDimension(Dimensions targetMove) {
		this.x += targetMove.getX();
		this.y += targetMove.getY();
	}
}
