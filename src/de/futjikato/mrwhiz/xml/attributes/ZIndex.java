package de.futjikato.mrwhiz.xml.attributes;

public class ZIndex extends XmlAttribute {

	private Float zIndex = new Float("0.0");
	
	@Override
	public void handleValue(String value) throws AttributeInvalidInput {
		this.zIndex = new Float(value);
		
		if(this.zIndex > 1 || this.zIndex < -1) {
			throw new AttributeInvalidInput();
		}
	}
	
	public Float getZIndex() {
		return this.zIndex;
	}

}
