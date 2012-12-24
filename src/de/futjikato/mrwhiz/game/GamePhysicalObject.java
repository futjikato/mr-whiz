package de.futjikato.mrwhiz.game;

import de.futjikato.mrwhiz.Physical;
import de.futjikato.mrwhiz.xml.Block;
import de.futjikato.mrwhiz.xml.BlockCollector;

public abstract class GamePhysicalObject extends Physical {

	@Override
	protected int yCol(float x, float y, int blocksize) {
		Block frontblock = BlockCollector.getInstance().getBlockByPixel(x, y);
		if (frontblock != null) {
			this.hitBlock(frontblock, Physical.COLLISION_Y_BLOCKED);
			return Physical.COLLISION_Y_BLOCKED;
		}

		Block backblock = BlockCollector.getInstance().getBlockByPixel(x, y - getHeight());
		if (backblock != null) {
			this.hitBlock(backblock, Physical.COLLISION_Y_BLOCKED);
			return Physical.COLLISION_Y_BLOCKED;
		}

		return Physical.COLLISION_Y_NONE;
	}

	@Override
	protected int xCol(float x, float y, int blocksize) {
		Block frontblock = BlockCollector.getInstance().getBlockByPixel(x, y);
		if (frontblock != null) {
			this.hitBlock(frontblock, Physical.COLLISION_X_BLOCKED);
			return Physical.COLLISION_X_BLOCKED;
		}

		Block backblock = BlockCollector.getInstance().getBlockByPixel(x + getWidth(), y);
		if (backblock != null) {
			this.hitBlock(backblock, Physical.COLLISION_X_BLOCKED);
			return Physical.COLLISION_X_BLOCKED;
		}

		return Physical.COLLISION_X_NONE;
	}

	protected abstract int getHeight();

	protected abstract int getWidth();

	protected abstract void hitBlock(Block block, int type);
}
