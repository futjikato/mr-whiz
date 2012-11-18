package de.futjikato.mrwhiz.xml.attributes;

public class Spawn extends XmlAttribute {

	private int x;
	private int y;
	
	@Override
	public void handleValue(String value) throws AttributeInvalidInput {
		// check if at least one comma exists
		if(!value.contains(",")) {
			throw new AttributeInvalidInput();
		}
		
		// split string
		String[] splitted = value.split(",");

		// check array length ( must be 2 )
		if(splitted.length != 2) {
			throw new AttributeInvalidInput();
		}
		
		try {
			this.x = Integer.parseInt(splitted[0]);
			this.y = Integer.parseInt(splitted[1]);
		} catch (Exception e) {
			// catch all may occuring exceptions and throw an AttributeInvalidInput instead
			throw new AttributeInvalidInput();
		}
	}
	
	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}
}