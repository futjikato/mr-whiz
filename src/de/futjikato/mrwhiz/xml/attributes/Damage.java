package de.futjikato.mrwhiz.xml.attributes;

public class Damage extends XmlAttribute {

	protected int damage = 0;

	@Override
	public void handleValue(String value) throws AttributeInvalidInput {
		this.damage = Integer.parseInt(value);
	}

	public int getDemaga() {
		return this.damage;
	}
}
