package de.futjikato.mrwhiz.xml.attributes;

import de.futjikato.mrwhiz.xml.XmlObject;

public class Blocksize extends XmlAttribute {

	private int blocksize;

	@Override
	public void handleValue(String value, XmlObject xmlObject) throws AttributeInvalidInput {
		this.blocksize = Integer.parseInt(value);
	}

	public int getBlocksize() {
		return blocksize;
	}
}
