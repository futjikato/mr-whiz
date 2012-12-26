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

	volume {
		@Override
		public XmlAttribute getAttribute() {
			return new Volume();
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
	},

	repeatable {
		@Override
		public XmlAttribute getAttribute() {
			return new Repeatable();
		}
	},

	pause {
		@Override
		public XmlAttribute getAttribute() {
			return new Pause();
		}
	};

	public abstract XmlAttribute getAttribute();
}
