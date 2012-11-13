package de.futjikato.mrwhiz.xml.attributes;

public enum XmlAttributeTypes {
	
	xywh {
		@Override
		public XmlAttribute getAttribute() {
			return new Dimensions();
		}
	},
	
	scale {
		@Override
		public XmlAttribute getAttribute() {
			return new Dimensions();
		}
	};
	
	public abstract XmlAttribute getAttribute();
}
