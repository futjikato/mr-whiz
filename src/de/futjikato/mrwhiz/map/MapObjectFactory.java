package de.futjikato.mrwhiz.map;

public class MapObjectFactory {
	
	public static MapObject getMapObject(String name) throws MapObjectTypeNotDefiniedException {
		if(name.equals("world")) {
			return new MapWorld();
		} else if(name.equals("decoratrions")) {
			return MapDecorationCollector.getInstance();
		} else if(name.equals("decoration")) {
			return new MapDecoration();
		} else {
			throw new MapObjectTypeNotDefiniedException(name);
		}
	}
}
