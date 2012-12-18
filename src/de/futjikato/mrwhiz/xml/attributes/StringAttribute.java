package de.futjikato.mrwhiz.xml.attributes;

import de.futjikato.mrwhiz.xml.XmlObject;

public abstract class StringAttribute extends XmlAttribute {

	protected String value;

	protected XmlObject ref;

	@Override
	public void handleValue(String value, XmlObject xmlObject) throws AttributeInvalidInput {
		this.value = value;
		this.ref = xmlObject;
	}

	@Override
	public StringAttribute copyFrom(XmlAttribute o) throws AttributeCopyError {
		if (!(o instanceof StringAttribute)) {
			throw new AttributeCopyError();
		}

		StringAttribute oString = (StringAttribute) o;
		this.value = oString.getValue();

		return this;
	}

	protected StringAttribute setValue(String value) {
		this.value = value;
		return this;
	}

	public String getValue() {
		return this.value;
	}

	public XmlObject getReference() {
		return this.ref;
	}
}
