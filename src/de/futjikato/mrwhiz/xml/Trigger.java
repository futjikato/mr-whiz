package de.futjikato.mrwhiz.xml;

import de.futjikato.mrwhiz.xml.attributes.Id;
import de.futjikato.mrwhiz.xml.attributes.Target;
import de.futjikato.mrwhiz.xml.attributes.XmlAttribute;

public class Trigger extends XmlObject {

	private Event refEvent;

	private String triggerKey;

	public enum Triggers {
		touch() {
			@Override
			public void addListenerToTarget(Trigger caller) {
				XmlAttribute targetAttr = caller.getAttribute("target");
				if (targetAttr != null && targetAttr instanceof Target) {
					Target target = (Target) targetAttr;
					XmlObject xmlo = Id.getReferenceById(target.getValue());

					if (xmlo instanceof Block) {
						Block block = (Block) xmlo;
						block.addTouchListener(caller);
					}
				}
			}
		};

		public abstract void addListenerToTarget(Trigger caller);
	}

	@Override
	public void handleValue(String currentValue) throws ObjectNoValueSupport {
		this.triggerKey = currentValue;
	}

	@Override
	public void addChildObj(XmlObject mapObj) throws ObjectNoChildSupport, ObjectInvalidChild {
		throw new ObjectNoChildSupport();
	}

	public void bindEvent(Event event) {
		this.refEvent = event;

		// execute trigger binding
		try {
			Triggers trigger = Triggers.valueOf(this.triggerKey);
			trigger.addListenerToTarget(this);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			/* unknown trigger -> do nothing */
		}
	}

	public void trigger() {
		this.refEvent.trigger();
	}
}