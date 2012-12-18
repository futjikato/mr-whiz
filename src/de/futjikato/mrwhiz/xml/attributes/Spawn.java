package de.futjikato.mrwhiz.xml.attributes;

import de.futjikato.mrwhiz.xml.XmlObject;

public class Spawn extends XmlAttribute {

	private int x;
	private int y;

	@Override
	public void handleValue(String value, XmlObject xmlObject) throws AttributeInvalidInput {
		// check if at least one comma exists
		if (!value.contains(",")) {
			throw new AttributeInvalidInput();
		}

		// split string
		String[] splitted = value.split(",");

		// check array length ( must be 2 )
		if (splitted.length != 2) {
			throw new AttributeInvalidInput();
		}

		try {
			this.x = Integer.parseInt(splitted[0]);
			this.y = Integer.parseInt(splitted[1]);
		} catch (Exception e) {
			// catch all may occuring exceptions and throw an
			// AttributeInvalidInput instead
			throw new AttributeInvalidInput();
		}
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	@Override
	public XmlAttribute copyFrom(XmlAttribute o) throws AttributeCopyError {
		if (!(o instanceof Spawn)) {
			throw new AttributeCopyError();
		}

		Spawn oSpawn = (Spawn) o;

		this.x = oSpawn.x;
		this.y = oSpawn.y;

		return this;
	}
}
