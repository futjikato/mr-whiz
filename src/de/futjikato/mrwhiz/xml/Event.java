package de.futjikato.mrwhiz.xml;

import java.util.ArrayList;
import java.util.List;

import de.futjikato.mrwhiz.game.GameTimeTrigger;
import de.futjikato.mrwhiz.game.events.CallbackEvent;
import de.futjikato.mrwhiz.xml.attributes.Delay;
import de.futjikato.mrwhiz.xml.attributes.XmlAttribute;

public class Event extends XmlObject {

	private List<Trigger> triggers = new ArrayList<Trigger>();

	private List<Action> actions = new ArrayList<Action>();

	private List<Block> eventBlocks = new ArrayList<Block>();

	private boolean triggered = false;

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
		} else if (mapObj instanceof Block) {
			eventBlocks.add((Block) mapObj);
		} else {
			throw new ObjectInvalidChild();
		}
	}

	public void trigger() {

		if (this.triggered) {
			return;
		}
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

	public void addEventBlocks() {
		for ( Block newBlock : eventBlocks ) {
			try {
				BlockCollector.getInstance().addChildObj(newBlock);
			} catch (ObjectNoChildSupport e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ObjectInvalidChild e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void rebindTriggers() {
		for ( Trigger trigger : triggers ) {
			trigger.bindEvent(this);
		}
	}
}
