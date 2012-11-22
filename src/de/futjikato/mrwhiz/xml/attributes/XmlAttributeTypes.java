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
	},

	walkable {
		@Override
		public XmlAttribute getAttribute() {
			return new Walkable();
		}
	},

	spawn {
		@Override
		public XmlAttribute getAttribute() {
			return new Spawn();
		}
	},

	zIndex {
		@Override
		public XmlAttribute getAttribute() {
			return new ZIndex();
		}
	},

	speed {
		@Override
		public XmlAttribute getAttribute() {
			return new Speed();
		}
	},

	volume {
		@Override
		public XmlAttribute getAttribute() {
			return new Volume();
		}
	};

	public abstract XmlAttribute getAttribute();
}
