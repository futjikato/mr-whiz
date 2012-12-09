package de.futjikato.mrwhiz.xml;

import java.util.ArrayList;
import java.util.List;

public class EventCollector extends XmlObject {

	private List<Event> eventList = new ArrayList<Event>();

	private static EventCollector instance;

	private EventCollector() {

	}

	public static EventCollector getInstance() {
		if (instance == null) {
			instance = new EventCollector();
		}
		return instance;
	}

	@Override
	public void handleValue(String currentValue) throws ObjectNoValueSupport {
		throw new ObjectNoValueSupport();
	}

	@Override
	public void addChildObj(XmlObject mapObj) throws ObjectNoChildSupport, ObjectInvalidChild {
		if (mapObj instanceof Event) {
			this.eventList.add((Event) mapObj);
		} else {
			throw new ObjectInvalidChild();
		}
	}

}
