package de.futjikato.mrwhiz.xml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.futjikato.mrwhiz.xml.attributes.Dimensions;

public class ItemCollector extends XmlObject {

	private static ItemCollector instance;

	private HashMap<String, Item> areamap = new HashMap<String, Item>();

	private ItemCollector() {

	}

	public static ItemCollector getInstance() {
		if (instance == null) {
			instance = new ItemCollector();
		}
		return instance;
	}

	@Override
	public void handleValue(String currentValue) throws ObjectNoValueSupport {
		throw new ObjectNoValueSupport();
	}

	@Override
	public void addChildObj(XmlObject mapObj) throws ObjectNoChildSupport, ObjectInvalidChild {
		if (mapObj instanceof Item) {
			Item item = (Item) mapObj;

			Dimensions dim = item.getDimensions();

			if (dim != null) {
				// insert reference for every block
				String key = String.format("%d,%d", dim.getX(), dim.getY());

				Item oItem = this.areamap.get(key);

				if (oItem == null) {
					// insert if no other level is on that grid
					this.areamap.put(key, item);
				} else {
					// throw error if duplicated level
					throw new ObjectInvalidChild("Duplicated item for coordinates (" + dim.getX() + "/" + dim.getY() + ")");
				}
			}
		} else {
			throw new ObjectInvalidChild("Invalid child type in items. Only elemts of type item are supported.");
		}
	}

	public List<Item> getItemsByBlockCoords(int bx, int by, int bw, int bh, int blocksize) {
		List<Item> list = new ArrayList<Item>();

		// run thought all requested blocks
		for ( int i = -20 ; i < bw ; i++ ) {
			for ( int j = -20 ; j < bh ; j++ ) {
				String key = String.format("%d,%d", bx + i, by + j);
				Item item = this.areamap.get(key);

				// add item if there is one
				if (item != null) {
					list.add(item);
				}
			}
		}

		return list;
	}

	public Item getItem(int bx, int by) {
		for ( int ox = 0 ; ox > -5 ; ox-- ) {
			for ( int oy = 0 ; oy > -5 ; oy-- ) {
				String key = String.format("%d,%d", bx + ox, by + oy);
				Item item = this.areamap.get(key);

				// check if there is a block
				if (item != null && (item.getDimensions().getW() >= (-ox + 1)) && (item.getDimensions().getH() >= (-oy + 1))) {
					return item;
				}
			}
		}

		return null;
	}

	public boolean removeItem(int bx, int by) {
		String key = String.format("%d,%d", bx, by);
		Item item = this.areamap.get(key);

		if (item != null) {
			this.areamap.remove(key);
			return true;
		}
		return false;
	}
}
