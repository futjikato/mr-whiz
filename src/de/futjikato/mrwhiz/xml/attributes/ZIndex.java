package de.futjikato.mrwhiz.xml.attributes;

import de.futjikato.mrwhiz.xml.XmlObject;

public class ZIndex extends FloatAttribute {

	@Override
	public void handleValue(String value, XmlObject xmlObject) throws AttributeInvalidInput {
		float val = Float.parseFloat(value);

		if (val > 1 || val < -1) {
			throw new AttributeInvalidInput();
		}

		this.setValue(val);
	}

	/**
	 * Deprecated<br>
	 * Use getValue from abstract class !
	 * 
	 * @return zIndex
	 */
	@Deprecated
	public Float getZIndex() {
		return this.getValue();
	}

}
