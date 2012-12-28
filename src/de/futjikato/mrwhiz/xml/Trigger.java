package de.futjikato.mrwhiz.xml;

import java.util.List;

import de.futjikato.mrwhiz.game.Block;
import de.futjikato.mrwhiz.xml.attributes.Target;
import de.futjikato.mrwhiz.xml.attributes.XmlAttribute;

public class Trigger extends XmlObject {

	private Event refEvent;

	private String triggerKey;

	public enum Triggers {
		touch() {
			@Override
			public void addListenerToTarget(Trigger caller) {
				String target = caller.getTarget();
				if (target == null) {
					return;
				}
				List<Block> blocks = BlockCollector.getInstance().getBlocksByName(target);

				for ( Block cBlock : blocks ) {
					cBlock.addTouchListener(caller);
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

	private String getTarget() {
		XmlAttribute xmlAttr = this.getAttribute("target");
		if (xmlAttr != null) {
			Target target = (Target) xmlAttr;
			return target.getValue();
		}

		return null;
	}
}