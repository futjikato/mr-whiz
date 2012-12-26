package de.futjikato.mrwhiz.game;

import de.futjikato.mrwhiz.xml.BlockCollector;
import de.futjikato.mrwhiz.xml.Trigger;

public class Item extends Block {

	private int score;

	public Item(int bx, int by, char type, BlockDefinitions defines) {
		super(bx, by, type, defines);

		score = defines.getBlockAttributeAsInt(type, "score", 0);
	}

	public int getScore() {
		return score;
	}

	@Override
	public void triggerTouch() {
		for ( Trigger trigger : touchListener ) {
			trigger.trigger();
		}
		// remove item after usage
		BlockCollector.getInstance().removeBlock(this);
	}
}
