package de.futjikato.mrwhiz.xml.attributes;

import de.futjikato.mrwhiz.xml.XmlObject;

public class Volume extends XmlAttribute {

	private float volume = 0.5f;

	@Override
	public void handleValue(String value, XmlObject xmlObject) throws AttributeInvalidInput {
		this.volume = Float.parseFloat(value);
	}

	public float getVolume() {
		return volume;
	}
}
