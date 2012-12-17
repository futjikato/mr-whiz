package de.futjikato.mrwhiz.xml;

import de.futjikato.mrwhiz.game.GameTimeTrigger;
import de.futjikato.mrwhiz.game.events.CallbackEvent;
import de.futjikato.mrwhiz.xml.attributes.Delay;
import de.futjikato.mrwhiz.xml.attributes.Dimensions;
import de.futjikato.mrwhiz.xml.attributes.Id;
import de.futjikato.mrwhiz.xml.attributes.Target;
import de.futjikato.mrwhiz.xml.attributes.XmlAttribute;

public class Action extends XmlObject {

	private enum Actions {

		destroyBlock() {
			@Override
			public void exec(Action caller) {
				XmlAttribute targetAttr = caller.getAttribute("target");
				if (targetAttr != null && targetAttr instanceof Target) {
					Target target = (Target) targetAttr;
					XmlObject xmlo = Id.getReferenceById(target.getValue());

					if (xmlo instanceof Block) {
						Block block = (Block) xmlo;
						BlockCollector.getInstance().removeBlock(block);
					}
				}
			}
		},

		moveBlock() {
			@Override
			public void exec(Action caller) {
				XmlAttribute targetAttr = caller.getAttribute("target");
				XmlAttribute targetMovDimAttr = caller.getAttribute("xywh");

				if (!(targetMovDimAttr instanceof Dimensions)) {
					targetMovDimAttr = new Dimensions();
				}
				Dimensions targetMove = (Dimensions) targetMovDimAttr;

				if (targetAttr != null && targetAttr instanceof Target) {
					Target target = (Target) targetAttr;
					XmlObject xmlo = Id.getReferenceById(target.getValue());

					if (xmlo instanceof Block) {
						Block block = (Block) xmlo;

						block.moveByDimension(targetMove);
					}
				}
			}
		},

		createEventBlocks() {
			@Override
			public void exec(Action caller) {
				caller.triggeredEvent.addEventBlocks();
			}
		},

		rebindEvent() {
			@Override
			public void exec(Action caller) {
				XmlAttribute targetAttr = caller.getAttribute("target");

				if (targetAttr != null && targetAttr instanceof Target) {
					Target target = (Target) targetAttr;
					XmlObject xmlo = Id.getReferenceById(target.getValue());

					if (xmlo instanceof Event) {
						Event evt = (Event) xmlo;

						evt.rebindTriggers();
					}
				}
			}
		};

		public abstract void exec(Action caller);
	}

	private Actions action;

	private Event triggeredEvent;

	@Override
	public void handleValue(String currentValue) throws ObjectNoValueSupport {
		try {
			Actions action = Actions.valueOf(currentValue);
			this.action = action;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			/* unknown action -> won´t do anything on exec */
		}
	}

	@Override
	public void addChildObj(XmlObject mapObj) throws ObjectNoChildSupport, ObjectInvalidChild {
		throw new ObjectNoChildSupport();
	}

	public void exec(Event event) {

		this.triggeredEvent = event;

		if (this.action != null) {

			// get delay
			int delay = this.getDelay();

			if (delay > 0) {
				GameTimeTrigger.getInstance().addEvent(new CallbackEvent(new Runnable() {
					@Override
					public void run() {
						Action.this.action.exec(Action.this);
					}
				}, delay));
			} else {
				// no delay
				Action.this.action.exec(Action.this);
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
}