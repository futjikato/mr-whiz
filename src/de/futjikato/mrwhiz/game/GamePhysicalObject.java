package de.futjikato.mrwhiz.game;

import de.futjikato.mrwhiz.Physical;
import de.futjikato.mrwhiz.xml.Block;
import de.futjikato.mrwhiz.xml.BlockCollector;

public abstract class GamePhysicalObject extends Physical {

	@Override
	protected boolean yCol(float x, float y, int blocksize) {
		// get block coords
		int by = (int) Math.floor(y / blocksize);
		int bx = (int) Math.floor(x / blocksize);

		// calc block height
		int bh = this.getHeight() / blocksize;

		boolean free = true;
		for ( int j = 0 ; j < bh ; j++ ) {
			Block block = BlockCollector.getInstance().getBlock(bx, by + j);
			if (block != null) {
				free = false;
				this.hitBlock(block);
				break;
			}
		}

		return free;
	}

	@Override
	protected boolean xCol(float x, float y, int blocksize) {
		// get block coords
		int by = (int) Math.floor(y / blocksize);
		int bx = (int) Math.floor(x / blocksize);

		// calc block height
		int bw = this.getWidth() / blocksize;

		boolean free = true;
		for ( int j = 0 ; j < bw ; j++ ) {
			Block block = BlockCollector.getInstance().getBlock(bx + j, by);
			if (block != null) {
				free = false;
				this.hitBlock(block);
				break;
			}
		}

		return free;
	}

	protected abstract int getHeight();

	protected abstract int getWidth();

	protected abstract void hitBlock(Block block);
}
