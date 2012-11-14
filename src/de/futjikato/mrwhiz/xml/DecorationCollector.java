package de.futjikato.mrwhiz.xml;

import java.util.HashMap;
import java.util.Stack;

import org.xml.sax.Attributes;

import de.futjikato.mrwhiz.xml.attributes.Dimensions;
import de.futjikato.mrwhiz.xml.attributes.XmlAttribute;

public class DecorationCollector extends XmlObject {
	
	private static DecorationCollector instance;
	private HashMap<String, Stack<Decoration>> decoMap = new HashMap<String, Stack<Decoration>>();
	
	public static DecorationCollector getInstance() {
		if(DecorationCollector.instance == null) {
			DecorationCollector.instance = new DecorationCollector();
		}
		
		return DecorationCollector.instance;
	}

	@Override
	public void handleAttributes(Attributes attributes) {
		return;
	}

	@Override
	public void handleValue(String currentValue) throws ObjectNoValueSupport {
		throw new ObjectNoValueSupport();
	}

	@Override
	public void addChildObj(XmlObject mapObj) {
		
		// filter all objects except for MapDecorations
		if(!(mapObj instanceof Decoration)) {
			return;
		}
		
		// get dimension attributes
		XmlAttribute dimAttr = mapObj.getAttribute("xywh");
		if(!(dimAttr instanceof Dimensions)) {
			return;
		}
		Dimensions dim = (Dimensions)dimAttr;
		
		// generate hashmap key
		String key = String.format("%d,%d", dim.getX(), dim.getY());
		
		// get stack from hashmap
		Stack<Decoration> stack = this.decoMap.get(key);
		
		// check if a stack exists at the key
		if(stack == null) {
			stack = new Stack<Decoration>();
		}
		
		// add the given mapObj to the stack
		stack.push((Decoration) mapObj);
	}
	
	
}
