package de.futjikato.mrwhiz.xml;

import java.util.List;

import de.futjikato.mrwhiz.game.Block;
import de.futjikato.mrwhiz.game.GameTimeTrigger;
import de.futjikato.mrwhiz.game.events.CallbackEvent;
import de.futjikato.mrwhiz.xml.attributes.Delay;
import de.futjikato.mrwhiz.xml.attributes.Id;
import de.futjikato.mrwhiz.xml.attributes.Target;
import de.futjikato.mrwhiz.xml.attributes.XmlAttribute;

public class Action extends XmlObject {

	private enum Actions {

		hideBlocks() {
			@Override
			public void exec(Action caller) {
				String target = caller.getTarget();
				if (target == null) {
					return;
				}

				BlockCollector bc = BlockCollector.getInstance();
				List<Block> blocks = bc.getBlocksByName(target);

				for ( Block cBlock : blocks ) {
					cBlock.setDoRender(false);
				}
			}
		},

		showBlocks() {
			@Override
			public void exec(Action caller) {
				String target = caller.getTarget();
				if (target == null) {
					return;
				}

				BlockCollector bc = BlockCollector.getInstance();
				List<Block> blocks = bc.getBlocksByName(target);

				for ( Block cBlock : blocks ) {
					cBlock.setDoRender(true);
				}
			}
		},

		toggleBlocks() {
			@Override
			public void exec(Action caller) {
				String target = caller.getTarget();
				if (target == null) {
					return;
				}

				BlockCollector bc = BlockCollector.getInstance();
				List<Block> blocks = bc.getBlocksByName(target);

				for ( Block cBlock : blocks ) {
					cBlock.setDoRender(!cBlock.doRender());
				}
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

	private String getTarget() {
		XmlAttribute xmlAttr = this.getAttribute("target");
		if (xmlAttr != null) {
			Target target = (Target) xmlAttr;
			return target.getValue();
		}

		return null;
	}
}