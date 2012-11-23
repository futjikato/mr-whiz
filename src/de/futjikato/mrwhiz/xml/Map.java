package de.futjikato.mrwhiz.xml;

public class Map extends XmlObject {

	private String xmlFilePath;

	@Override
	public void handleValue(String currentValue) throws ObjectNoValueSupport {
		this.xmlFilePath = currentValue;
	}

	@Override
	public void addChildObj(XmlObject mapObj) throws ObjectNoChildSupport, ObjectInvalidChild {
		throw new ObjectNoChildSupport();
	}

	public XmlReader getReader() {
		return new XmlReader(this.xmlFilePath);
	}
}
