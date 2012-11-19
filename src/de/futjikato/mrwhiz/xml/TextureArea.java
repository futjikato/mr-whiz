package de.futjikato.mrwhiz.xml;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import de.futjikato.mrwhiz.Renderable;
import de.futjikato.mrwhiz.xml.attributes.Dimensions;
import de.futjikato.mrwhiz.xml.attributes.Walkable;
import de.futjikato.mrwhiz.xml.attributes.XmlAttribute;

public class TextureArea extends XmlObject implements Renderable {

	private Texture texture;

	@Override
	public void handleValue(String currentValue) throws ObjectNoValueSupport {
		throw new ObjectNoValueSupport();
	}

	@Override
	public void addChildObj(XmlObject mapObj) {
		if(mapObj instanceof Texture) {
			this.texture = (Texture) mapObj;
		}
	}

	@Override
	public void draw() {
		
		// get dimension attributes
		XmlAttribute dimAttr = this.attrs.get("xywh");
		if(!(dimAttr instanceof Dimensions)) {
			return;
		}
		Dimensions dim = (Dimensions)dimAttr;
		
		Image img = this.texture.getImage();
		
		Graphics graph = new Graphics();
		graph.fillRect(dim.getX() * 50, dim.getY() * 50, dim.getW() * 50, dim.getH() * 50, img, 0, 0);
	}
	
	public boolean isWalkable() {
		XmlAttribute attr = this.attrs.get("walkable");
		if(attr == null) {
			attr = new Walkable();
			this.attrs.put("walkable", attr);
		}
		
		if(!(attr instanceof Walkable)) {
			return false;
		}
		
		Walkable walkable = (Walkable) attr;
		return walkable.getWalkable();
	}

	@Override
	protected void initAttributeFallback() {
		XmlAttribute dim = this.attrs.get("xywh");
		if(dim == null) {
			dim = new Dimensions();
			this.attrs.put("xywh", dim);
		}
	}
}
