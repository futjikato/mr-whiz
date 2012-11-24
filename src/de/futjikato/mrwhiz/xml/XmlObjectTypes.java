package de.futjikato.mrwhiz.xml;

public enum XmlObjectTypes {

	worldmap {
		public XmlObject getType() {
			return new Worldmap();
		}
	},

	gamemap {
		public XmlObject getType() {
			return new Gamemap();
		}
	},

	decorations {
		public XmlObject getType() {
			return DecorationCollector.getInstance();
		}
	},

	textureAreas {
		public XmlObject getType() {
			return TextureAreaCollector.getInstance();
		}
	},

	area {
		public XmlObject getType() {
			return new TextureArea();
		}
	},

	texture {
		public XmlObject getType() {
			return new Texture();
		}
	},

	levels {
		public XmlObject getType() {
			return LevelCollector.getInstance();
		}
	},

	level {
		public XmlObject getType() {
			return new Level();
		}
	},

	map {
		public XmlObject getType() {
			return new Map();
		}
	},

	music {
		public XmlObject getType() {
			return new Music();
		}
	};

	public abstract XmlObject getType();
}
