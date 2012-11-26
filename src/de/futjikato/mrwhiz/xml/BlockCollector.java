package de.futjikato.mrwhiz.xml;

import java.util.HashMap;

import de.futjikato.mrwhiz.xml.attributes.Dimensions;

public final class BlockCollector extends XmlObject {

	private HashMap<String, Block> areamap = new HashMap<String, Block>();

	private static BlockCollector instance;

	private BlockCollector() {

	}

	public static BlockCollector getInstance() {
		if (instance == null) {
			instance = new BlockCollector();
		}
		return instance;
	}

	@Override
	public void handleValue(String currentValue) throws ObjectNoValueSupport {
		throw new ObjectNoValueSupport();
	}

	@Override
	public void addChildObj(XmlObject mapObj) throws ObjectNoChildSupport, ObjectInvalidChild {
		if (mapObj instanceof Block) {
			Block block = (Block) mapObj;
			Dimensions dim = block.getDimensions();
			String key = String.format("%d,%d", dim.getX(), dim.getY());

			this.areamap.put(key, block);
		}
	}

	public void drawBlocks(int bx, int by, int bw, int bh, int blocksize) {
		// run thought all requested blocks
		for ( int i = -20 ; i < bw ; i++ ) {
			for ( int j = -20 ; j < bh ; j++ ) {
				String key = String.format("%d,%d", bx + i, by + j);
				Block block = this.areamap.get(key);

				// draw area if there is one
				if (block != null) {
					block.draw(blocksize);
				}
			}
		}
	}

	public boolean isFree(int bx, int by) {
		String key = String.format("%d,%d", bx, by);
		Block block = this.areamap.get(key);

		return block == null;
	}
}
