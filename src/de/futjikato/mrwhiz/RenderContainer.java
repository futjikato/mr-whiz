package de.futjikato.mrwhiz;

import de.futjikato.mrwhiz.xml.XmlObject;

public interface RenderContainer {
	public XmlObject getObjects(int x, int y, int width, int height);
}
