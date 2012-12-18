package de.futjikato.mrwhiz.xml.attributes;

public class Volume extends FloatAttribute {

	/**
	 * Deprecated<br>
	 * Use getValue from abstract class !
	 * 
	 * @return volume
	 */
	@Deprecated
	public float getVolume() {
		return getValue();
	}
}
