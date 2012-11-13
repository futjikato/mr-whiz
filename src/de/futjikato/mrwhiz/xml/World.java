package de.futjikato.mrwhiz.xml;

import java.util.Stack;

public class World extends XmlObject {

	private TextureAreaCollector areaCollector;
	
	@Override
	public void handleValue(String currentValue) throws ObjectNoValueSupport {
		throw new ObjectNoValueSupport();
	}

	@Override
	public void addChildObj(XmlObject mapObj) throws ObjectNoChildSupport, ObjectInvalidChild {
		
		// add texture area collector
		if(mapObj instanceof TextureAreaCollector) {
			this.areaCollector = (TextureAreaCollector) mapObj;
		}
		
		// throw new ObjectInvalidChild();
	}
	
	public Stack<TextureArea> getTextureAreas() {
		return this.areaCollector.getTextureAreas();
	}
	
}
