package de.futjikato.mrwhiz.xml;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TextureAreaCollector extends XmlObject {

	private List<TextureArea> list = new ArrayList<TextureArea>();

	private static TextureAreaCollector instance;

	public static TextureAreaCollector getInstance() {
		if (TextureAreaCollector.instance == null) {
			TextureAreaCollector.instance = new TextureAreaCollector();
		}

		return TextureAreaCollector.instance;
	}

	@Override
	public void handleValue(String currentValue) throws ObjectNoValueSupport {
		throw new ObjectNoValueSupport();
	}

	@Override
	public void addChildObj(XmlObject mapObj) {
		if (mapObj instanceof TextureArea) {
			TextureArea area = (TextureArea) mapObj;
			this.list.add(area);
			Collections.sort(this.list);
		}
	}

	public void draw(float vpx, float vpy, int blocksize) {
		for ( TextureArea area : this.list ) {
			area.draw(vpx, vpy, blocksize);
		}
	}

	public void clean() {
		this.list.clear();
	}
}