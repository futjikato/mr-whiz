package de.futjikato.mrwhiz.xml.attributes;

import de.futjikato.mrwhiz.xml.XmlObject;

public class Target extends XmlAttribute {

	private String value;

	@Override
	public void handleValue(String value, XmlObject xmlObject) throws AttributeInvalidInput {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}

}
