package de.futjikato.mrwhiz.xml.attributes;

import de.futjikato.mrwhiz.xml.XmlObject;

/**
 * Abstract class for an XML attribute representation
 */
public abstract class XmlAttribute {

	/**
	 * Converts the XML string input into the internal used storage format
	 * 
	 * @param value
	 * @param xmlObject
	 * @throws AttributeInvalidInput
	 */
	public abstract void handleValue(String value, XmlObject xmlObject) throws AttributeInvalidInput;

	/**
	 * Copy all values from other
	 * 
	 * @param other
	 * @throws AttributeCopyError
	 */
	public abstract void copyFrom(XmlAttribute o) throws AttributeCopyError;
}
