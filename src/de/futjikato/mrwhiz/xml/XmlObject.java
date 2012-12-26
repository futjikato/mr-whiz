package de.futjikato.mrwhiz.xml;

import java.util.HashMap;
import java.util.Map;

import org.xml.sax.Attributes;

import de.futjikato.mrwhiz.xml.attributes.AttributeInvalidInput;
import de.futjikato.mrwhiz.xml.attributes.Dimensions;
import de.futjikato.mrwhiz.xml.attributes.XmlAttribute;
import de.futjikato.mrwhiz.xml.attributes.XmlAttributeTypes;

public abstract class XmlObject {

	protected Map<String, XmlAttribute> attrs = new HashMap<String, XmlAttribute>();

	public abstract void handleValue(String currentValue) throws ObjectNoValueSupport;

	public abstract void addChildObj(XmlObject mapObj) throws ObjectNoChildSupport, ObjectInvalidChild;

	protected void complete() throws ObjectIncomplete {
		// do nothing in default implementation
	}

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
			} catch (AttributeInvalidInput e) {
				e.printStackTrace();
			}
		}
	}

	public XmlAttribute getAttribute(String name) {
		return this.attrs.get(name);
	}

	public Dimensions getDimensions() {
		XmlAttribute xmlAttr = this.getAttribute("xywh");
		if (!(xmlAttr instanceof Dimensions)) {
			return new Dimensions();
		}

		return (Dimensions) xmlAttr;
	}
}
