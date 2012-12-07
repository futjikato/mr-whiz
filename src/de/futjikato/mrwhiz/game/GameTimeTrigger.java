package de.futjikato.mrwhiz.game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.lwjgl.Sys;

import de.futjikato.mrwhiz.game.events.Event;

public class GameTimeTrigger implements Runnable {

	private static GameTimeTrigger instance;
	private Thread thread;
	private long lastRun;

	private List<Event> eventList;

	private GameTimeTrigger() {
		this.eventList = new ArrayList<Event>();
		this.startThread();
	}

	private void startThread() {

		if (this.thread != null) {
			if (!Thread.interrupted()) {
				this.thread.interrupt();
			}
		}

		this.thread = new Thread(this, "GameTimeTrigger");
		this.thread.start();
		this.lastRun = this.getTime();
	}

	public static GameTimeTrigger getInstance() {
		if (instance == null) {
			instance = new GameTimeTrigger();
		}

		return instance;
	}

	/**
	 * Get the accurate system time
	 * 
	 * @return The system time in milliseconds
	 */
	public long getTime() {
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}

	@Override
	public void run() {
		while (!Thread.interrupted()) {
			long now = this.getTime();
			if (now > this.lastRun + 1000) {
				long decreaseSice = ((now - this.lastRun) / 1000);
				this.lastRun = now;

				this.decreaseEventDelay(decreaseSice);
				this.triggerReadyEvents();

				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					System.out.println("GameTimeTrigger thread has interrupted. Set up new one.");
					this.startThread();
				}
			}
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

	public synchronized void addEvent(Event event) {
		this.eventList.add(event);
	}
}
