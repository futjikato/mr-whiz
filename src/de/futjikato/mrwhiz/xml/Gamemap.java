package de.futjikato.mrwhiz.xml;

import de.futjikato.mrwhiz.xml.attributes.Blocksize;
import de.futjikato.mrwhiz.xml.attributes.XmlAttribute;

public class Gamemap extends XmlObject {

	private Blocksize blocksize;

	@Override
	public void handleValue(String currentValue) throws ObjectNoValueSupport {
		throw new ObjectNoValueSupport();
	}

	@Override
	public void addChildObj(XmlObject mapObj) throws ObjectNoChildSupport, ObjectInvalidChild {
		// TODO Auto-generated method stub

	}

	public Blocksize getBlocksize() {
		if (this.blocksize == null) {
			XmlAttribute blockAttr = this.getAttribute("blocksize");

			if (blockAttr instanceof Blocksize) {
				this.blocksize = (Blocksize) blockAttr;
			}
		}

		return this.blocksize;
	}
}
