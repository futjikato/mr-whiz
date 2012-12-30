package de.futjikato.mrwhiz.game;

import java.util.List;

import de.futjikato.mrwhiz.Physical;
import de.futjikato.mrwhiz.xml.BlockCollector;

public abstract class GamePhysicalObject extends Physical {

	@Override
	protected boolean checkCollision(float x, float y, int type) {
		boolean free = true;

		List<Block> blocklist = BlockCollector.getInstance().getBlocks(x, y, x + getWidth(), y - getHeight());
		if (blocklist.size() > 0) {
			for ( Block cBlock : blocklist ) {
				if (cBlock.doRender()) {
					if (cBlock.hasCollision()) {
						free = false;
					}
					hitBlock(cBlock, type);
				}
			}
		}

		return free;
	}

	protected abstract int getHeight();

	protected abstract int getWidth();

	protected abstract void hitBlock(Block block, int type);
}
