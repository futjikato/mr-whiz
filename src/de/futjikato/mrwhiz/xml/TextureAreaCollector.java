package de.futjikato.mrwhiz.xml;

import java.util.Stack;

import org.xml.sax.Attributes;

public class TextureAreaCollector extends XmlObject {

	private Stack<TextureArea> areas = new Stack<TextureArea>();

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
			this.areas.push((TextureArea) mapObj);
		}
	}

}
