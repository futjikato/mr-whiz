package de.futjikato.mrwhiz.xml.attributes;

public class Repeat extends XmlAttribute {

	private int sh;
	private int sv;
	
	@Override
	public void handleValue(String value) {
		// value can be "10,10"
		String[] parts = value.split(",");
		
		if(parts.length == 2) {
			this.sh = Integer.parseInt(parts[0]);
			this.sv = Integer.parseInt(parts[1]);
		}
		
		if(parts.length == 1) {
			this.sh = Integer.parseInt(parts[0]);
			this.sv = Integer.parseInt(parts[0]);
		}
	}
	
	public int getSh() {
		return sh;
	}

	public int getSv() {
		return sv;
	}
}
