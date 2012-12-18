package de.futjikato.mrwhiz.xml.attributes;

public class Pause extends IntegerAttribute {

	/**
	 * Deprecated<br>
	 * Use getValue from abstract class !
	 * 
	 * @return time
	 */
	@Deprecated
	public int getTime() {
		return this.getValue();
	}
}
