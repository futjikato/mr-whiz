package de.futjikato.mrwhiz.game.events;


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
	 * If timeable is true the event will be triggered when delay is 0 or below<br>
	 * Set this to false to trigger it manually
	 */
	protected boolean timeable = true;

	/**
	 * Decrease the delay time If 0 and timeable is true the event will be
	 * triggered
	 * 
	 * @param decreaseSice
	 */
	public void decreaseDelay(long decreaseSice) {
		if (this.delay > 0) {
			this.delay -= decreaseSice;
		}
	}

	/**
	 * Check if timeable is true and delay is 0
	 * 
	 * @return true if timeable is true and delay is 0
	 */
	public boolean isReady() {
		return (this.timeable && (this.delay <= 0));
	}

	public abstract void trigger();
}
