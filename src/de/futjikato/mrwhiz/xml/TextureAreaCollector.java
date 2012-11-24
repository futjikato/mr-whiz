package de.futjikato.mrwhiz.xml;

import java.util.HashMap;

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
	public void handleValue(String currentValue) throws ObjectNoValueSupport {
		throw new ObjectNoValueSupport();
	}

	@Override
	public void addChildObj(XmlObject mapObj) {
		if (mapObj instanceof TextureArea) {
			int count = 0;
			TextureArea area = (TextureArea) mapObj;

			XmlAttribute attr = area.getAttribute("xywh");

			if (attr instanceof Dimensions) {
				Dimensions dim = (Dimensions) attr;

				// insert reference for every block
				for ( int i = 0 ; i < dim.getW() ; i++ ) {
					for ( int j = 0 ; j < dim.getH() ; j++ ) {
						String key = String.format("%d,%d", dim.getX() + i, dim.getY() + j);

						TextureArea oArea = this.areamap.get(key);

						if (oArea == null) {
							// insert if no other area is on that grid
							this.areamap.put(key, area);
							count++;
						} else {
							// insert if current area if above or on the same
							// level ( order in xml counts )
							if (area.compareTo(oArea) >= 0) {
								this.areamap.put(key, area);
								count++;
							}
						}
					}
				}
			}

			System.out.println("Added " + count + " texture blocks.");
		}
	}

	public TextureArea getArea(int x, int y) {
		String key = String.format("%d,%d", x, y);
		return this.areamap.get(key);
	}

	public void drawBlocks(int bx, int by, int bw, int bh) {
		// run thought all requested blocks
		for ( int i = 0 ; i < bw ; i++ ) {
			for ( int j = 0 ; j < bh ; j++ ) {
				String key = String.format("%d,%d", bx + i, by + j);
				TextureArea area = this.areamap.get(key);

				// draw area if there is one
				if (area != null) {
					area.drawBlock(bx + i, by + j);
				}
			}
		}
	}

	public void clean() {
		this.areamap.clear();
	}
}