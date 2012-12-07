package de.futjikato.mrwhiz.game.events;

public class NoChangeEvent<T> extends Event {

	private NoChangeEventCallback<T> callback;
	private T testValue;

	public interface NoChangeEventCallback<T> {
		public void run(T oldvalue);
	}

	public NoChangeEvent(T oldValue, NoChangeEventCallback<T> cb, int delay) {
		this.testValue = oldValue;
		this.callback = cb;
		this.delay = delay;
	}

	@Override
	public void trigger() {
		if (this.callback != null) {
			this.callback.run(this.testValue);
		}
	}
}
