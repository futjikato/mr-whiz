package de.futjikato.mrwhiz.xml.attributes;

import de.futjikato.mrwhiz.xml.XmlObject;

public abstract class XmlAttribute implements Cloneable {
	public abstract void handleValue(String value, XmlObject xmlObject) throws AttributeInvalidInput;

	public XmlAttribute clone() {
		try {
			return (XmlAttribute) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}
}
