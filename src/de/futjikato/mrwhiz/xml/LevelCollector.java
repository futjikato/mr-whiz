package de.futjikato.mrwhiz.xml;

import org.xml.sax.Attributes;

public class LevelCollector extends XmlObject {

	private static LevelCollector instance;
	
	private LevelCollector() {
		
	}
	
	public static LevelCollector getInstance() {
		if(LevelCollector.instance == null) {
			LevelCollector.instance = new LevelCollector();
		}
		
		return LevelCollector.instance;
	}
	
	@Override
	public void handleAttributes(Attributes attributes) {
		// TODO Auto-generated method stub

	}

	@Override
	public void handleValue(String currentValue) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addChildObj(XmlObject mapObj) {
		// TODO Auto-generated method stub

	}
}
