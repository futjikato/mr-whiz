package de.futjikato.mrwhiz.game.inventory;

import java.util.List;

import de.futjikato.mrwhiz.game.Block;
import de.futjikato.mrwhiz.game.Door;
import de.futjikato.mrwhiz.game.GamePlayer;
import de.futjikato.mrwhiz.xml.BlockCollector;

public class Key extends Tool {

	public Key() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void use(GamePlayer player) {
		// search for doors near player
		List<Block> nearBlocks = BlockCollector.getInstance().getBlocks(player.getX() - player.getWidth() - 50, player.getY() + player.getHeight() + 50, player.getX() + 50, player.getY() - 50);

		for ( Block cBlock : nearBlocks ) {
			if (cBlock instanceof Door) {
				Door cDoor = (Door) cBlock;
				cDoor.openDoor();
			}
		}
	}
}
