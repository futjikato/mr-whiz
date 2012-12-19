package de.futjikato.mrwhiz.xml.attributes;

import de.futjikato.mrwhiz.xml.XmlObject;

public abstract class IntegerAttribute extends XmlAttribute {

	protected int value;

	protected XmlObject ref;

	@Override
	public void handleValue(String value, XmlObject xmlObject) throws AttributeInvalidInput {
		this.value = Integer.parseInt(value);
		this.ref = xmlObject;
	}

	@Override
	public void copyFrom(XmlAttribute o) throws AttributeCopyError {
		if (!(o instanceof IntegerAttribute)) {
			throw new AttributeCopyError();
		}

		IntegerAttribute oInt = (IntegerAttribute) o;
		this.value = oInt.getValue();
	}

	protected IntegerAttribute setValue(int value) {
		this.value = value;
		return this;
	}

	public int getValue() {
		return this.value;
	}

	public XmlObject getReference() {
		return this.ref;
	}
}
