package de.futjikato.mrwhiz.xml.attributes;

public class Blocksize extends XmlAttribute {

	private int blocksize;

	@Override
	public void handleValue(String value) throws AttributeInvalidInput {
		this.blocksize = Integer.parseInt(value);
	}

	public int getBlocksize() {
		return blocksize;
	}
}
