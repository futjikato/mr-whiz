package de.futjikato.mrwhiz.game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import de.futjikato.mrwhiz.Util;
import de.futjikato.mrwhiz.game.events.Event;

public class GameTimeTrigger {

	private static GameTimeTrigger instance;
	private long lastRun;

	private List<Event> eventList;

	private GameTimeTrigger() {
		this.eventList = new ArrayList<Event>();
	}

	/**
	 * Singleton
	 * 
	 * @return singleton instance
	 */
	public static GameTimeTrigger getInstance() {
		if (instance == null) {
			instance = new GameTimeTrigger();
		}

		return instance;
	}

	public void update() {
		long now = Util.getTime();
		if (now > this.lastRun + 1000) {
			long decreaseSice = ((now - this.lastRun) / 1000);
			this.lastRun = now;

			this.decreaseEventDelay(decreaseSice);
			this.triggerReadyEvents();
		}
	}

	/**
	 * Check all events if they are ready<br>
	 * trigger ready events and remove them from the internal list
	 */
	private synchronized void triggerReadyEvents() {
		Iterator<Event> iter = this.eventList.iterator();
		while (iter.hasNext()) {
			Event event = iter.next();
			if (event.isReady()) {
				iter.remove();
				event.trigger();
			}
		}
	}

	/**
	 * Decrease delay of all events stored in the internal list
	 * 
	 * @param Seconds
	 *            Amount of seconds
	 */
	private synchronized void decreaseEventDelay(long decreaseSice) {
		for ( Event event : this.eventList ) {
			event.decreaseDelay(decreaseSice);
		}
	}

	/**
	 * Add an event to the list
	 * 
	 * @param event
	 */
	public synchronized void addEvent(Event event) {
		this.eventList.add(event);
	}
}
