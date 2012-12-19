package de.futjikato.mrwhiz.xml.attributes;

import de.futjikato.mrwhiz.xml.XmlObject;

public class Repeat extends XmlAttribute {

	private boolean repeatX = false;
	private boolean repeatY = false;
	private int repeatXBy = 0;
	private int repeatYBy = 0;

	@Override
	public void handleValue(String value, XmlObject xmlObject) {
		if (value.equals("x")) {
			this.repeatX = true;
		} else if (value.equals("y")) {
			this.repeatY = true;
		} else if (value.equals("xy") || value.equals("yx")) {
			this.repeatY = true;
			this.repeatX = true;
		} else {
			// TODO implement reapeat with specific values
		}
	}

	public boolean isRepeatX() {
		return repeatX;
	}

	public boolean isRepeatY() {
		return repeatY;
	}

	public int getRepeatXBy() {
		return repeatXBy;
	}

	public int getRepeatYBy() {
		return repeatYBy;
	}

	@Override
	public void copyFrom(XmlAttribute o) throws AttributeCopyError {
		if (!(o instanceof Repeat)) {
			throw new AttributeCopyError();
		}

		Repeat oRep = (Repeat) o;

		this.repeatX = oRep.repeatX;
		this.repeatY = oRep.repeatY;
		this.repeatXBy = oRep.repeatXBy;
		this.repeatYBy = oRep.repeatYBy;
	}
}
