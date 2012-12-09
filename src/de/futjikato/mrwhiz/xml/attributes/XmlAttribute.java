package de.futjikato.mrwhiz.xml.attributes;

import de.futjikato.mrwhiz.xml.XmlObject;

public abstract class XmlAttribute {
	public abstract void handleValue(String value, XmlObject xmlObject) throws AttributeInvalidInput;
}
