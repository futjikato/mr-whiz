package de.futjikato.mrwhiz.game.events;

import de.futjikato.mrwhiz.Util;

/**
 * Abstract Event class.<br>
 * Should be used for all timeable events in the game
 */
public abstract class Event {

	/**
	 * Delay till execution
	 */
	protected int delay;

	/**
	 * Timestamp of the time the event was created<br>
	 * Get time from Util.getTime()
	 */
	protected long createdTime;

	/**
	 * If timeable is true the event will be triggered when delay is 0 or below<br>
	 * Set this to false to trigger it manually
	 */
	protected boolean timeable = true;

	/**
	 * Check if timeable is true and delay is 0
	 * 
	 * @return true if timeable is true and delay is 0
	 */
	public boolean isReady() {

		// check for non initalized values
		if (this.delay <= 0 || this.createdTime <= 0) {
			return true;
		}

		long now = Util.getTime();
		return now >= (this.createdTime + (this.delay * 1000));
	}

	public abstract void trigger();
}
