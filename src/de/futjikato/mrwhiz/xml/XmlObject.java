package de.futjikato.mrwhiz.xml;

import java.util.HashMap;

import org.xml.sax.Attributes;

import de.futjikato.mrwhiz.ObjectConfigs;
import de.futjikato.mrwhiz.xml.attributes.XmlAttribute;
import de.futjikato.mrwhiz.xml.attributes.XmlAttributeTypes;

public abstract class XmlObject {
	
	protected HashMap<String, XmlAttribute> attrs = new HashMap<String, XmlAttribute>();
	
	public abstract void handleValue(String currentValue);
	public abstract void addChildObj(XmlObject mapObj) throws ObjectNoChildSupport;
	
	public void handleAttributes(Attributes attributes) {
		for(int i = 0 ; i < attributes.getLength() ; i++) {
			// get attribute name
			String name = attributes.getQName(i);
			
			// load attribute class from enum
			XmlAttributeTypes type = XmlAttributeTypes.valueOf(name);
			XmlAttribute attr = type.getAttribute();
		}
	}
	
	public ObjectConfigs getConfigObject() {
		return this.conf;
	}
}
