package de.futjikato.mrwhiz.xml.attributes;

import de.futjikato.mrwhiz.xml.XmlObject;

public abstract class FloatAttribute extends XmlAttribute {

	protected float value;

	protected XmlObject ref;

	@Override
	public void handleValue(String value, XmlObject xmlObject) throws AttributeInvalidInput {
		this.value = Float.parseFloat(value);
		this.ref = xmlObject;
	}

	@Override
	public FloatAttribute copyFrom(XmlAttribute o) throws AttributeCopyError {
		if (!(o instanceof FloatAttribute)) {
			throw new AttributeCopyError();
		}

		FloatAttribute oFloat = (FloatAttribute) o;
		this.value = oFloat.getValue();

		return this;
	}

	protected FloatAttribute setValue(float value) {
		this.value = value;
		return this;
	}

	public float getValue() {
		return this.value;
	}

	public XmlObject getReference() {
		return this.ref;
	}

}
