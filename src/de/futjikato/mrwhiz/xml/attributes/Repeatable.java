package de.futjikato.mrwhiz.xml.attributes;

import de.futjikato.mrwhiz.xml.XmlObject;

public class Repeatable extends XmlAttribute {

	private boolean isRepeatable;

	@Override
	public void handleValue(String value, XmlObject xmlObject) throws AttributeInvalidInput {
		this.isRepeatable = value.toLowerCase().equals("yes");
	}

	public boolean isRepeatable() {
		return this.isRepeatable;
	}
}
