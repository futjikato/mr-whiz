package de.futjikato.mrwhiz.xml;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.xml.sax.Attributes;

import de.futjikato.mrwhiz.xml.attributes.AttributeInvalidInput;
import de.futjikato.mrwhiz.xml.attributes.Dimensions;
import de.futjikato.mrwhiz.xml.attributes.XmlAttribute;
import de.futjikato.mrwhiz.xml.attributes.XmlAttributeTypes;

public abstract class XmlObject {

	protected Map<String, XmlAttribute> orgAttrs = new HashMap<String, XmlAttribute>();

	protected Map<String, XmlAttribute> attrs = new HashMap<String, XmlAttribute>();

	public abstract void handleValue(String currentValue) throws ObjectNoValueSupport;

	public abstract void addChildObj(XmlObject mapObj) throws ObjectNoChildSupport, ObjectInvalidChild;

	public void handleAttributes(Attributes attributes) {
		for ( int i = 0 ; i < attributes.getLength() ; i++ ) {
			// get attribute name
			String name = attributes.getQName(i);

			// load attribute class from enum
			XmlAttributeTypes type = XmlAttributeTypes.valueOf(name);
			XmlAttribute attr = type.getAttribute();

			// push value to attribute object and add attribute to list
			try {
				attr.handleValue(attributes.getValue(i), this);
				this.attrs.put(name, attr);
				XmlAttribute clonedOrg = (XmlAttribute) attr.clone();
				this.orgAttrs.put(name, clonedOrg);
			} catch (AttributeInvalidInput e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// after all attributes are handled we call initAttributeFallback so any
		// missing attribute can be replaced by fallback values
		this.initAttributeFallback();
	}

	protected void initAttributeFallback() {
		// no implementation in abstract class
		// overwrite this if needed
	}

	public XmlAttribute getAttribute(String name) {
		return this.attrs.get(name);
	}

	/**
	 * Easy getter for often used attributes
	 */

	public Dimensions getDimensions() {
		XmlAttribute xmlAttr = this.getAttribute("xywh");
		if (!(xmlAttr instanceof Dimensions)) {
			return new Dimensions();
		}

		return (Dimensions) xmlAttr;
	}

	public void restoreOriginalAttributes() {
		HashMap<String, XmlAttribute> clonedList = new HashMap<String, XmlAttribute>();
		for ( Entry<String, XmlAttribute> cursor : this.orgAttrs.entrySet() ) {
			clonedList.put(cursor.getKey(), cursor.getValue());
		}
		this.attrs = clonedList;
	}
}
