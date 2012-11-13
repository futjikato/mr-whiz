package de.futjikato.mrwhiz.xml.attributes;

public enum XmlAttributeTypes {
	
	xywh {
		@Override
		public XmlAttribute getAttribute() {
			return new Dimensions();
		}
	},
	
	repeat {
		@Override
		public XmlAttribute getAttribute() {
			return new Repeat();
		}
	};
	
	public abstract XmlAttribute getAttribute();
}
