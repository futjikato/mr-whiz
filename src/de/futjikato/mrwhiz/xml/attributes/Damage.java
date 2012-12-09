package de.futjikato.mrwhiz.xml.attributes;

import de.futjikato.mrwhiz.xml.XmlObject;

public class Damage extends XmlAttribute {

	protected int damage = 0;

	@Override
	public void handleValue(String value, XmlObject xmlObject) throws AttributeInvalidInput {
		this.damage = Integer.parseInt(value);
	}

	public int getDemaga() {
		return this.damage;
	}
}
