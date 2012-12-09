package de.futjikato.mrwhiz.xml;

import java.util.ArrayList;
import java.util.List;

public class Event extends XmlObject {

	private List<Trigger> triggers = new ArrayList<Trigger>();

	private List<Action> actions = new ArrayList<Action>();

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
		for ( Action act : this.actions ) {
			act.exec();
		}
	}
}
