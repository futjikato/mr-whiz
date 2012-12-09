package de.futjikato.mrwhiz.xml.attributes;

import de.futjikato.mrwhiz.xml.XmlObject;

public class Walkable extends XmlAttribute {

	private boolean walkable = false;

	@Override
	public void handleValue(String value, XmlObject xmlObject) {
		this.walkable = value.toUpperCase().equals("TRUE");
	}

	public boolean getWalkable() {
		return this.walkable;
	}

}
