package de.futjikato.mrwhiz.xml;

import org.newdawn.slick.SlickException;

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
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

}
