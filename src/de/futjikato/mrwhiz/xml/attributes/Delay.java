package de.futjikato.mrwhiz.xml.attributes;

import de.futjikato.mrwhiz.xml.XmlObject;

public class Delay extends XmlAttribute {

	private int seconds;

	@Override
	public void handleValue(String value, XmlObject xmlObject) throws AttributeInvalidInput {
		this.seconds = Integer.parseInt(value);
	}

	public int getValue() {
		return this.seconds;
	}
}
