package de.futjikato.mrwhiz.xml;

import java.util.HashMap;
import java.util.Stack;

import org.xml.sax.Attributes;

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
	public void handleValue(String currentValue) {
		return;
	}

	@Override
	public void addChildObj(XmlObject mapObj) {
		
		// filter all objects except for MapDecorations
		if(!(mapObj instanceof Decoration)) {
			return;
		}
		
		// generate hashmap key
		String key = String.format("%d,%d", mapObj.getConfigObject().getX(), mapObj.getConfigObject().getY());
		
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
