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

	decorations {
		@Override
		public XmlObject getType() {
			return DecorationCollector.getInstance();
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

	items {
		@Override
		public XmlObject getType() {
			return ItemCollector.getInstance();
		}
	},

	item {
		@Override
		public XmlObject getType() {
			return new Item();
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

	spawnpoints {
		@Override
		public XmlObject getType() {
			return SpawnpointCollector.getInstance();
		}
	},

	spawn {
		@Override
		public XmlObject getType() {
			return new Spawnpoint();
		}
	};

	public abstract XmlObject getType();
}
