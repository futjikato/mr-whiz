package de.futjikato.mrwhiz.xml.attributes;

public class Walkable extends XmlAttribute {

	private boolean walkable = false;
	
	@Override
	public void handleValue(String value) {
		this.walkable = value.toUpperCase().equals("TRUE");
	}
	
	public boolean getWalkable() {
		return this.walkable;
	}

}
