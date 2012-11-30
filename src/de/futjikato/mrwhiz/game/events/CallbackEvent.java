package de.futjikato.mrwhiz.game.events;

public class CallbackEvent extends Event {

	private Runnable callback;

	public CallbackEvent(Runnable cb, int delay) {
		this.callback = cb;
		this.delay = delay;
	}

	@Override
	public void trigger() {
		if (this.callback != null) {
			this.callback.run();
		}
	}

}
