package de.futjikato.mrwhiz.xml.attributes;


public class Repeatable extends BooleanAttribute {

	/**
	 * Deprecated<br>
	 * Use getValue from abstract class !
	 * 
	 * @return true if repeatable
	 */
	@Deprecated
	public boolean isRepeatable() {
		return this.getValue();
	}
}
