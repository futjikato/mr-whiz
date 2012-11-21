package de.futjikato.mrwhiz.xml.attributes;

public class Speed extends XmlAttribute {

	private float speed = 1.0f;
	
	@Override
	public void handleValue(String value) throws AttributeInvalidInput {
		this.speed = Float.parseFloat(value);
	}

	public float getSpeed() {
		return this.speed;
	}
}
