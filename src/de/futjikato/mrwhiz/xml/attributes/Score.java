package de.futjikato.mrwhiz.xml.attributes;

import de.futjikato.mrwhiz.xml.XmlObject;

public class Score extends XmlAttribute {

	private int score;

	@Override
	public void handleValue(String value, XmlObject xmlObject) throws AttributeInvalidInput {
		this.score = Integer.parseInt(value);
	}

	public int getValue() {
		return this.score;
	}
}
