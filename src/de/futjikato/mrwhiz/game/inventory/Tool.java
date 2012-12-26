package de.futjikato.mrwhiz.game.inventory;

import de.futjikato.mrwhiz.game.GamePlayer;

public abstract class Tool {

	private enum Toolbelt {
		Key() {
			@Override
			protected Tool getTool() {
				return new Key();
			}
		};

		protected abstract Tool getTool();
	}

	public abstract void use(GamePlayer player);

	public static Tool getTool(String name) {
		return Toolbelt.valueOf(name).getTool();
	}
}
