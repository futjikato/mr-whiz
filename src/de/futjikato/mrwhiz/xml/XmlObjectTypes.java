package de.futjikato.mrwhiz.xml;

public enum XmlObjectTypes {

	worldmap {
		@Override
		public XmlObject getType() {
			return new Worldmap();
		}
	},

	gamemap {
		@Override
		public XmlObject getType() {
			return Gamemap.getInstance();
		}
	},

	textureAreas {
		@Override
		public XmlObject getType() {
			return TextureAreaCollector.getInstance();
		}
	},

	area {
		@Override
		public XmlObject getType() {
			return new TextureArea();
		}
	},

	texture {
		@Override
		public XmlObject getType() {
			return new Texture();
		}
	},

	levels {
		@Override
		public XmlObject getType() {
			return LevelCollector.getInstance();
		}
	},

	level {
		@Override
		public XmlObject getType() {
			return new Level();
		}
	},

	map {
		@Override
		public XmlObject getType() {
			return new Map();
		}
	},

	music {
		@Override
		public XmlObject getType() {
			return new Music();
		}
	},

	blocks {
		@Override
		public XmlObject getType() {
			return BlockCollector.getInstance();
		}
	},

	blockfile {
		@Override
		public XmlObject getType() {
			return new Blockfile();
		}
	},

	events {
		@Override
		public XmlObject getType() {
			return EventCollector.getInstance();
		}
	},

	event {
		@Override
		public XmlObject getType() {
			return new Event();
		}
	},

	trigger {
		@Override
		public XmlObject getType() {
			return new Trigger();
		}
	},

	action {
		@Override
		public XmlObject getType() {
			return new Action();
		}
	},

	file() {
		@Override
		public XmlObject getType() {
			return new File();
		}
	},

	routes() {
		@Override
		public XmlObject getType() {
			// TODO this is an unused class. try to get rid of it
			return new RouteCollector();
		}
	},

	route() {
		@Override
		public XmlObject getType() {
			return new RouteConnector();
		}
	};

	public abstract XmlObject getType();
}
