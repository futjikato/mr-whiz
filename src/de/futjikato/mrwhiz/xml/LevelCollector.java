package de.futjikato.mrwhiz.xml;

import java.util.HashMap;

import de.futjikato.mrwhiz.xml.attributes.Dimensions;
import de.futjikato.mrwhiz.xml.attributes.XmlAttribute;

public class LevelCollector extends XmlObject {

	private static final int SEARCH_RADIUS = 10;

	private static LevelCollector instance;

	private HashMap<String, Level> areamap = new HashMap<String, Level>();

	private LevelCollector() {

	}

	public static LevelCollector getInstance() {
		if (LevelCollector.instance == null) {
			LevelCollector.instance = new LevelCollector();
		}

		return LevelCollector.instance;
	}

	@Override
	public void handleValue(String currentValue) throws ObjectNoValueSupport {
		throw new ObjectNoValueSupport();
	}

	@Override
	public void addChildObj(XmlObject mapObj) throws ObjectInvalidChild {
		if (mapObj instanceof Level) {
			Level level = (Level) mapObj;

			XmlAttribute attr = level.getAttribute("xywh");

			if (attr instanceof Dimensions) {
				Dimensions dim = (Dimensions) attr;

				// insert reference for every block
				String key = String.format("%d,%d", dim.getX(), dim.getY());

				Level oLevel = this.areamap.get(key);

				if (oLevel == null) {
					// insert if no other level is on that grid
					this.areamap.put(key, level);
				} else {
					// throw error if duplicated level
					throw new ObjectInvalidChild("Duplicated level for coordinates (" + dim.getX() + "/" + dim.getY() + ")");
				}
			}
		} else {
			throw new ObjectInvalidChild("Invalid child type in levels. Only level are supported.");
		}
	}

	public void drawBlocks(int bx, int by, int bw, int bh, int blocksize) {
		// run thought all requested blocks
		for ( int i = 0 ; i < bw ; i++ ) {
			for ( int j = 0 ; j < bh ; j++ ) {
				String key = String.format("%d,%d", bx + i, by + j);
				Level lvl = this.areamap.get(key);

				// draw level if there is one
				if (lvl != null) {
					lvl.draw(blocksize);
				}
			}
		}
	}

	public Level getLevelOnBlock(int bx, int by) {
		Level level = null;

		// run thought all possible blocks
		for ( int i = -SEARCH_RADIUS ; i < SEARCH_RADIUS ; i++ ) {
			for ( int j = -SEARCH_RADIUS ; j < SEARCH_RADIUS ; j++ ) {
				String key = String.format("%d,%d", bx + i, by + j);
				Level lvl = this.areamap.get(key);

				// check the found
				if (lvl != null && lvl.inTriggerArea(bx, by)) {
					level = lvl;
				}
			}
		}

		return level;
	}

	public void clean() {
		this.areamap.clear();
	}
}
