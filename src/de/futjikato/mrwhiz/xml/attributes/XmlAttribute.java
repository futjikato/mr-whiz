package de.futjikato.mrwhiz.xml.attributes;

public abstract class XmlAttribute {
	public abstract void handleValue(String value) throws AttributeInvalidInput;
}
