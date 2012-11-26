package de.futjikato.mrwhiz.game;

import de.futjikato.mrwhiz.Physical;
import de.futjikato.mrwhiz.xml.BlockCollector;

public abstract class GamePhysicalObject extends Physical {

	private boolean still = true;

	protected boolean isStill() {
		return this.still;
	}

	@Override
	protected boolean checkCollide(float x, float y, int blocksize) {

		// get block coords
		int bx = (int) Math.floor(x / blocksize);
		int by = (int) Math.floor(y / blocksize);

		boolean retVal = BlockCollector.getInstance().isFree(bx, by);

		// save still state
		this.still = !retVal;

		return retVal;
	}
}
