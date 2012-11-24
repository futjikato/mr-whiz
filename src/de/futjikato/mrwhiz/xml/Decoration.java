package de.futjikato.mrwhiz.xml;

import org.newdawn.slick.Image;

import de.futjikato.mrwhiz.xml.attributes.Dimensions;
import de.futjikato.mrwhiz.xml.attributes.XmlAttribute;

public class Decoration extends XmlObject {

	private Texture texture;

	@Override
	public void handleValue(String currentValue) throws ObjectNoValueSupport {
		throw new ObjectNoValueSupport();
	}

	@Override
	public void addChildObj(XmlObject mapObj) {
		if (mapObj instanceof Texture) {
			this.texture = (Texture) mapObj;
		}
	}

	public void draw() {
		// get dimension attributes
		XmlAttribute dimAttr = this.attrs.get("xywh");
		if (!(dimAttr instanceof Dimensions)) {
			return;
		}
		Dimensions dim = (Dimensions) dimAttr;

		Image img = this.texture.getImage();

		img.draw(dim.getX(), dim.getY(), dim.getW(), dim.getH());
	}

	@Override
	protected void initAttributeFallback() {
		XmlAttribute dim = this.attrs.get("xywh");
		if (dim == null) {
			dim = new Dimensions();
			this.attrs.put("xywh", dim);
		}
	}
}
