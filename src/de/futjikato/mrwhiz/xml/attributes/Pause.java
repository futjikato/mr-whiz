package de.futjikato.mrwhiz.xml.attributes;

import de.futjikato.mrwhiz.xml.XmlObject;

public class Pause extends XmlAttribute {

	private int pauseTime;

	@Override
	public void handleValue(String value, XmlObject xmlObject) throws AttributeInvalidInput {
		this.pauseTime = Integer.parseInt(value);
	}

	public int getTime() {
		return this.pauseTime;
	}
}
