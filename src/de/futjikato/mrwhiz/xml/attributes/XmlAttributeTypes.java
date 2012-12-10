package de.futjikato.mrwhiz.xml.attributes;

public enum XmlAttributeTypes {

	blocksize {
		@Override
		public XmlAttribute getAttribute() {
			return new Blocksize();
		}
	},

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
	},

	damage {
		@Override
		public XmlAttribute getAttribute() {
			return new Damage();
		}
	},

	score {
		@Override
		public XmlAttribute getAttribute() {
			return new Score();
		}
	},

	id {
		@Override
		public XmlAttribute getAttribute() {
			return new Id();
		}
	},

	target {
		@Override
		public XmlAttribute getAttribute() {
			return new Target();
		}
	},

	delay {
		@Override
		public XmlAttribute getAttribute() {
			return new Delay();
		}
	};

	public abstract XmlAttribute getAttribute();
}
