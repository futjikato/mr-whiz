package de.futjikato.mrwhiz.xml;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import de.futjikato.mrwhiz.map.MapRenderer;
import de.futjikato.mrwhiz.xml.attributes.Dimensions;

public class Level extends XmlObject {

	private Texture texture;
	private Map map;

	@Override
	public void handleValue(String currentValue) throws ObjectNoValueSupport {
		throw new ObjectNoValueSupport();
	}

	@Override
	public void addChildObj(XmlObject mapObj) throws ObjectInvalidChild {
		if (mapObj instanceof Texture) {
			this.texture = (Texture) mapObj;
		} else if (mapObj instanceof Map) {
			this.map = (Map) mapObj;
		} else {
			throw new ObjectInvalidChild();
		}
	}

	public void draw() {
		// TODO stop using MapRenderer in generell implementation of XML tag
		// object
		Image img = this.texture.getImage();
		Graphics graph = new Graphics();

		int x = this.getDimensions().getX() * MapRenderer.BLOCKSIZE;
		int y = this.getDimensions().getY() * MapRenderer.BLOCKSIZE;
		int w = this.getDimensions().getW() * MapRenderer.BLOCKSIZE;
		int h = this.getDimensions().getH() * MapRenderer.BLOCKSIZE;

		graph.drawImage(img, x, y, x + w, y + h, 0, 0, img.getWidth(), img.getHeight());
	}

	public boolean inTriggerArea(int bx, int by, int offset) {
		Dimensions dim = this.getDimensions();
		return (dim.getX() - offset < bx && dim.getX() + dim.getW() + offset > bx && dim.getY() - offset < by && dim.getY() + dim.getH() + offset > by);
	}

	public boolean inTriggerArea(int bx, int by) {
		return this.inTriggerArea(bx, by, 1);
	}

	public Map getMap() {
		return map;
	}
}
