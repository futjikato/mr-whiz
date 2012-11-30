package de.futjikato.mrwhiz.xml.attributes;

public class Score extends XmlAttribute {

	private int score;

	@Override
	public void handleValue(String value) throws AttributeInvalidInput {
		this.score = Integer.parseInt(value);
	}

	public int getValue() {
		return this.score;
	}
}
