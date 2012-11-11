package de.futjikato.mrwhiz.map;

import org.xml.sax.Attributes;

public class MapDecorationCollector extends MapObject {
	private static MapDecorationCollector instance;
	
	private MapDecorationCollector() {
		
	}
	
	public static MapDecorationCollector getInstance() {
		if(MapDecorationCollector.instance == null) {
			MapDecorationCollector.instance = new MapDecorationCollector();
		}
		
		return MapDecorationCollector.instance;
	}

	@Override
	public void handleAttributes(Attributes attributes) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleValue(String currentValue) {
		// TODO Auto-generated method stub
		
	}
}
