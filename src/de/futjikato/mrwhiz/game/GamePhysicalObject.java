package de.futjikato.mrwhiz.game;

import de.futjikato.mrwhiz.Physical;
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
			if (!BlockCollector.getInstance().isFree(bx, by - j)) {
				free = false;
				break;
			}
		}

		return free;
	}

	protected boolean xCol(float x, float y, int blocksize) {
		// get block coords
		int by = (int) Math.floor(y / blocksize);
		int bx = (int) Math.floor(x / blocksize);

		// calc block height
		int bw = this.getWidth() / blocksize;

		boolean free = true;
		for ( int j = 0 ; j < bw ; j++ ) {
			if (!BlockCollector.getInstance().isFree(bx + j, by)) {
				free = false;
				break;
			}
		}

		return free;
	}

	protected abstract int getHeight();

	protected abstract int getWidth();
}
