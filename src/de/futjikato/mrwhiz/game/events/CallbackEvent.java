package de.futjikato.mrwhiz.game.events;

import de.futjikato.mrwhiz.Util;

public class CallbackEvent extends Event {

	private Runnable callback;

	public CallbackEvent(Runnable cb, int delay) {
		this.callback = cb;
		this.delay = delay;
		this.createdTime = Util.getTime();
	}

	@Override
	public void trigger() {
		if (this.callback != null) {
			this.callback.run();
		}
	}

}
