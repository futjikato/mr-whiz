package de.futjikato.mrwhiz.xml;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import de.futjikato.mrwhiz.xml.attributes.Dimensions;
import de.futjikato.mrwhiz.xml.attributes.Walkable;
import de.futjikato.mrwhiz.xml.attributes.XmlAttribute;
import de.futjikato.mrwhiz.xml.attributes.ZIndex;

public class TextureArea extends XmlObject implements Comparable<TextureArea> {

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
	
	public void drawBlock(int x, int y) {
		Image img = this.texture.getImage();
		Graphics graph = new Graphics();
		
		graph.fillRect(x * 50, y * 50, 50, 50, img, 0, 0);
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
		
		XmlAttribute zIndex = this.attrs.get("zIndex");
		if(zIndex == null) {
			zIndex = new ZIndex();
			this.attrs.put("zIndex", zIndex);
		}
	}

	@Override
	public int compareTo(TextureArea o) {
		XmlAttribute oAttr = o.getAttribute("zIndex");
		XmlAttribute mAttr = this.getAttribute("zIndex");
		
		if(!(oAttr instanceof ZIndex) || !(mAttr instanceof ZIndex)) {
			return 0;
		}
		
		ZIndex oIndex = (ZIndex) oAttr;
		ZIndex mIndex = (ZIndex) mAttr;
		
		return mIndex.getZIndex().compareTo(oIndex.getZIndex());
	}
}
