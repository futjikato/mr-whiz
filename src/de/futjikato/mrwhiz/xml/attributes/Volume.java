package de.futjikato.mrwhiz.xml.attributes;

public class Volume extends XmlAttribute {

	private float volume = 0.5f;

	@Override
	public void handleValue(String value) throws AttributeInvalidInput {
		this.volume = Float.parseFloat(value);
	}

	public float getVolume() {
		return volume;
	}
}
