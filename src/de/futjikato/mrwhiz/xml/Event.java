package de.futjikato.mrwhiz.xml;

import java.util.ArrayList;
import java.util.List;

import de.futjikato.mrwhiz.Util;
import de.futjikato.mrwhiz.game.GameTimeTrigger;
import de.futjikato.mrwhiz.game.events.CallbackEvent;
import de.futjikato.mrwhiz.xml.attributes.Delay;
import de.futjikato.mrwhiz.xml.attributes.Pause;
import de.futjikato.mrwhiz.xml.attributes.Repeatable;
import de.futjikato.mrwhiz.xml.attributes.XmlAttribute;

public class Event extends XmlObject {

	private List<Trigger> triggers = new ArrayList<Trigger>();

	private List<Action> actions = new ArrayList<Action>();

	private boolean triggered = false;

	private long lastTriggered;

	@Override
	public void handleValue(String currentValue) throws ObjectNoValueSupport {
		throw new ObjectNoValueSupport();
	}

	@Override
	public void addChildObj(XmlObject mapObj) throws ObjectNoChildSupport, ObjectInvalidChild {
		if (mapObj instanceof Trigger) {
			Trigger trigger = (Trigger) mapObj;
			trigger.bindEvent(this);
			triggers.add(trigger);
		} else if (mapObj instanceof Action) {
			actions.add((Action) mapObj);
		} else {
			throw new ObjectInvalidChild();
		}
	}

	public void trigger() {

		if (this.triggered) {
			// stop if one time event and already triggered
			if (!this.isRepeatable()) {
				return;
			}

			// stop if pause time is not over yet
			if (Util.getTime() <= (this.lastTriggered + (this.getPauseTime() * 1000))) {
				return;
			}
		}
		this.lastTriggered = Util.getTime();
		this.triggered = true;

		int delay = this.getDelay();

		if (delay > 0) {
			GameTimeTrigger.getInstance().addEvent(new CallbackEvent(new Runnable() {
				@Override
				public void run() {
					for ( Action act : Event.this.actions ) {
						act.exec(Event.this);
					}
				}
			}, delay));
		} else {
			// no delay
			for ( Action act : this.actions ) {
				act.exec(this);
			}
		}
	}

	private int getDelay() {
		XmlAttribute xmlAttr = this.getAttribute("delay");
		if (!(xmlAttr instanceof Delay)) {
			return 0;
		}

		Delay delayAttr = (Delay) xmlAttr;
		return delayAttr.getValue();
	}

	public void rebindTriggers() {
		this.triggered = false;
		for ( Trigger trigger : triggers ) {
			trigger.bindEvent(this);
		}
	}

	private boolean isRepeatable() {
		XmlAttribute xmlAttr = this.getAttribute("repeatable");
		if (xmlAttr instanceof Repeatable) {
			Repeatable reapAttr = (Repeatable) xmlAttr;
			return reapAttr.getValue();
		}
		return false;
	}

	private int getPauseTime() {
		XmlAttribute xmlAttr = this.getAttribute("repeatable");
		if (xmlAttr instanceof Pause) {
			Pause pauseAttr = (Pause) xmlAttr;
			return pauseAttr.getValue();
		}
		return 0;
	}
}
