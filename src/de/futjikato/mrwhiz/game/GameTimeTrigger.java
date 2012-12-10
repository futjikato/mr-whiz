package de.futjikato.mrwhiz.game;

import java.util.ArrayList;
import java.util.List;

import de.futjikato.mrwhiz.game.events.Event;

public class GameTimeTrigger {

	private static GameTimeTrigger instance;

	private boolean isTriggering = false;

	private List<Event> eventList;
	private List<Event> eventQueue;

	private GameTimeTrigger() {
		this.eventList = new ArrayList<Event>();
		this.eventQueue = new ArrayList<Event>();
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

		if (this.eventList.size() == 0) {
			return;
		}

		this.triggerReadyEvents();
	}

	/**
	 * Check all events if they are ready<br>
	 * trigger ready events and remove them from the internal list
	 */
	private void triggerReadyEvents() {

		// set trigger flag so the original list wont be modificated
		this.isTriggering = true;

		// trigger all saved events
		List<Event> newEventList = new ArrayList<Event>();
		for ( Event currEvent : this.eventList ) {
			if (currEvent.isReady()) {
				currEvent.trigger();
			} else {
				newEventList.add(currEvent);
			}
		}
		this.eventList = newEventList;

		// if new elements were edited within the trigger add those
		if (this.eventQueue.size() > 0) {
			this.eventList.addAll(this.eventQueue);
			this.eventQueue = new ArrayList<Event>();
		}

		// reset trigger flag
		this.isTriggering = false;
	}

	/**
	 * Add an event to the list.<br>
	 * If the trigger-loop is currently running the events will be added to the
	 * list after the loop.
	 * 
	 * @param event
	 */
	public void addEvent(Event event) {
		if (this.isTriggering) {
			this.eventQueue.add(event);
		} else {
			this.eventList.add(event);
		}
	}
}
