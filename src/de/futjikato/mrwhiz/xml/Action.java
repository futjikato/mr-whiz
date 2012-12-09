package de.futjikato.mrwhiz.xml;

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
		};

		public abstract void exec(Action caller);
	}

	private Actions action;

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

	public void exec() {
		Actions conenctedAction = this.action;
		if (conenctedAction != null) {
			conenctedAction.exec(this);
		}
	}
}