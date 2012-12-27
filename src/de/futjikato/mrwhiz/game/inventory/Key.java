package de.futjikato.mrwhiz.game.inventory;

import java.util.ConcurrentModificationException;
import java.util.List;

import de.futjikato.mrwhiz.game.Block;
import de.futjikato.mrwhiz.game.Door;
import de.futjikato.mrwhiz.game.GamePlayer;
import de.futjikato.mrwhiz.xml.BlockCollector;

public class Key extends Tool {

	@Override
	public void use(GamePlayer player) {
		// search for doors near player
		List<Block> nearBlocks = BlockCollector.getInstance().getBlocks(player.getX() - player.getWidth() - 50, player.getY() + player.getHeight() + 50, player.getX() + 50, player.getY() - 50);

		try {
			for ( Block cBlock : nearBlocks ) {
				if (cBlock instanceof Door) {
					Door cDoor = (Door) cBlock;
					cDoor.openDoor();
				}
			}
		} catch (ConcurrentModificationException e) {
			e.printStackTrace();
		}

		// TODO think about that one and may find proper solution
		player.getInventory().setItemRemoveFlag(this);
	}
}
