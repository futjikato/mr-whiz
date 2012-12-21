package de.futjikato.mrwhiz.xml;

import java.util.AbstractMap;
import java.util.HashMap;

public class SpawnpointCollector extends XmlObject {

	private AbstractMap<String, Spawnpoint> areamap = new HashMap<String, Spawnpoint>();

	private static SpawnpointCollector instance;

	private SpawnpointCollector() {
	}

	public static SpawnpointCollector getInstance() {
		if (null == instance) {
			instance = new SpawnpointCollector();
		}
		return instance;
	}

	@Override
	public void handleValue(String currentValue) throws ObjectNoValueSupport {
		throw new ObjectNoValueSupport();
	}

	@Override
	public void addChildObj(XmlObject mapObj) throws ObjectNoChildSupport, ObjectInvalidChild {
		if (mapObj instanceof Spawnpoint) {
			Spawnpoint sp = (Spawnpoint) mapObj;
			String key = String.format("%d,%d", sp.getDimensions().getX(), sp.getDimensions().getY());

			areamap.put(key, sp);
		}
	}

}
