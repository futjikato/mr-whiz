package de.futjikato.mrwhiz.game.events;

import de.futjikato.mrwhiz.game.GamePlayer;

public class EventRespawn extends Event {

	private GamePlayer player;

	public EventRespawn(GamePlayer player) {
		this.delay = 5;

		this.player = player;
	}

	@Override
	public void trigger() {
		this.player.respawn();
	}
}
