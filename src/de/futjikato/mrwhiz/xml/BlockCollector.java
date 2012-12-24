package de.futjikato.mrwhiz.xml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.futjikato.mrwhiz.game.GameRenderer;
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

	@Deprecated
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
		return (this.getBlock(bx, by) == null);
	}

	public List<Block> getBlocksByBlockCoords(int bx, int by, int bw, int bh, int blocksize) {

		List<Block> list = new ArrayList<Block>();

		// run thought all requested blocks
		for ( int i = -20 ; i < bw ; i++ ) {
			for ( int j = -20 ; j < bh ; j++ ) {
				String key = String.format("%d,%d", bx + i, by + j);
				Block block = this.areamap.get(key);

				// draw area if there is one
				if (block != null) {
					list.add(block);
				}
			}
		}

		return list;
	}

	public Block getBlock(int bx, int by) {
		for ( int ox = 0 ; ox > -20 ; ox-- ) {
			for ( int oy = 0 ; oy > -20 ; oy-- ) {
				String key = String.format("%d,%d", bx + ox, by + oy);
				Block block = this.areamap.get(key);

				// check if there is a block
				if (block != null && (block.getDimensions().getW() >= (-ox + 1)) && (block.getDimensions().getH() >= (-oy + 1))) {
					return block;
				}
			}
		}

		return null;
	}

	public void removeBlock(Block block) {
		Dimensions dim = block.getDimensions();
		if (dim != null) {
			String key = String.format("%d,%d", dim.getX(), dim.getY());
			this.areamap.remove(key);
		}
	}

	public Block getBlockByPixel(float x, float y) {
		// calc block dimension
		int blocksize = GameRenderer.getInstance().getBlocksize();
		int by = (int) Math.floor(y / blocksize);
		int bx = (int) Math.floor(x / blocksize);

		return getBlock(bx, by);
	}
}
