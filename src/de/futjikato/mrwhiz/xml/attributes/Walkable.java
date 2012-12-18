package de.futjikato.mrwhiz.xml.attributes;

public class Walkable extends BooleanAttribute {

	/**
	 * Deprecated<br>
	 * Use getValue from abstract class !
	 * 
	 * @return true if walkable
	 */
	@Deprecated
	public boolean getWalkable() {
		return this.getValue();
	}

}
