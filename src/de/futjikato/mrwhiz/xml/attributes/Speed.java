package de.futjikato.mrwhiz.xml.attributes;

import de.futjikato.mrwhiz.xml.XmlObject;

public class Speed extends XmlAttribute {

	private float speed = 1.0f;

	@Override
	public void handleValue(String value, XmlObject xmlObject) throws AttributeInvalidInput {
		this.speed = Float.parseFloat(value);
	}

	public float getSpeed() {
		return this.speed;
	}
}
