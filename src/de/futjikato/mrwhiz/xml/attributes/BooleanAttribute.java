package de.futjikato.mrwhiz.xml.attributes;

import de.futjikato.mrwhiz.xml.XmlObject;

public abstract class BooleanAttribute extends XmlAttribute {

	protected boolean value;

	protected XmlObject ref;

	@Override
	public void handleValue(String value, XmlObject xmlObject) throws AttributeInvalidInput {
		value = value.toLowerCase();
		this.value = (value.equals("yes") || value.equals("true"));

		this.ref = xmlObject;
	}

	@Override
	public void copyFrom(XmlAttribute o) throws AttributeCopyError {
		if (!(o instanceof BooleanAttribute)) {
			throw new AttributeCopyError();
		}

		BooleanAttribute oBol = (BooleanAttribute) o;
		this.value = oBol.getValue();
	}

	public boolean getValue() {
		return this.value;
	}

	protected BooleanAttribute setValue(boolean value) {
		this.value = value;
		return this;
	}

	public XmlObject getReference() {
		return this.ref;
	}
}
