package de.futjikato.mrwhiz.xml;

import java.util.HashMap;
import java.util.Stack;

import org.xml.sax.Attributes;

import de.futjikato.mrwhiz.xml.attributes.Dimensions;
import de.futjikato.mrwhiz.xml.attributes.XmlAttribute;

public class TextureAreaCollector extends XmlObject {

	private HashMap<String, TextureArea> areamap = new HashMap<String, TextureArea>();

	private static TextureAreaCollector instance;

	public static TextureAreaCollector getInstance() {
		if (TextureAreaCollector.instance == null) {
			TextureAreaCollector.instance = new TextureAreaCollector();
		}

		return TextureAreaCollector.instance;
	}

	@Override
	public void handleAttributes(Attributes attributes) {
		// TODO Auto-generated method stub

	}

	@Override
	public void handleValue(String currentValue) throws ObjectNoValueSupport {
		throw new ObjectNoValueSupport();
	}

	@Override
	public void addChildObj(XmlObject mapObj) {
		if (mapObj instanceof TextureArea) {
			TextureArea area = (TextureArea) mapObj;
			
			// Add the reference
			XmlAttribute attr = area.getAttribute("xywh");
			
			if(attr instanceof Dimensions) {
				Dimensions dim = (Dimensions) attr;
				int i = 0, j = 0;
				
				// insert reference for every block
				while(i < dim.getW()) {
					while(j < dim.getH()) {
						String key = String.format("%d,%d", dim.getX() + i, dim.getY() + j);
						this.areamap.put(key, area);
					}
				}
			}
		}
	}
	
	public TextureArea getArea(int bx, int by) {
		return this.areamap.get(String.format("%d,%d", bx, by));
	}
	
	public Stack<TextureArea> getAreas(int bx, int by, int bw, int bh) {
		Stack<TextureArea> areas = new Stack<TextureArea>();
		int i = 0, j = 0;
		
		while(i < bw) {
			while(j < bh) {
				String key = String.format("%d,%d", bx + i, by + j);
				areas.push(this.areamap.get(key));
			}
		}
		
		return areas;
	}
}
