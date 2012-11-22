package de.futjikato.mrwhiz.xml;

import org.newdawn.slick.SlickException;

import de.futjikato.mrwhiz.xml.attributes.Volume;
import de.futjikato.mrwhiz.xml.attributes.XmlAttribute;

public class Music extends XmlObject {

	private String filepath;

	@Override
	public void handleValue(String currentValue) throws ObjectNoValueSupport {
		this.filepath = currentValue;
	}

	@Override
	public void addChildObj(XmlObject mapObj) throws ObjectNoChildSupport, ObjectInvalidChild {
		throw new ObjectNoChildSupport();
	}

	public void play() {
		try {
			org.newdawn.slick.Music music = new org.newdawn.slick.Music(this.filepath);
			music.loop();

			Volume vol = this.getVolume();
			music.setVolume(vol.getVolume());
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	private Volume getVolume() {
		XmlAttribute volAttr = this.getAttribute("volume");

		if (volAttr == null || !(volAttr instanceof Volume)) {
			volAttr = new Volume();
		}

		return (Volume) volAttr;
	}
}
