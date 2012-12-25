package de.futjikato.mrwhiz.xml;

public class Trigger extends XmlObject {

	private Event refEvent;

	private String triggerKey;

	public enum Triggers {
		touch() {
			@Override
			public void addListenerToTarget(Trigger caller) {
				// TODO reimplement with new block storage
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