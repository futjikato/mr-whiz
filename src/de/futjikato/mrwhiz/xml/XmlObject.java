package de.futjikato.mrwhiz.xml;

import org.xml.sax.Attributes;

import de.futjikato.mrwhiz.ObjectConfigs;

public abstract class XmlObject {
	
	protected ObjectConfigs conf;
	
	public abstract void handleAttributes(Attributes attributes);
	public abstract void handleValue(String currentValue);
	public abstract void addChildObj(XmlObject mapObj) throws ObjectNoChildSupport;
	
	public ObjectConfigs getConfigObject() {
		return this.conf;
	}
}
