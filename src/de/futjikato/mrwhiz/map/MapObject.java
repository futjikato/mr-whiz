package de.futjikato.mrwhiz.map;

import org.xml.sax.Attributes;

public abstract class MapObject {
	public abstract void handleAttributes(Attributes attributes);
	public abstract void handleValue(String currentValue);
	public abstract void addChildObj(MapObject mapObj);
}
