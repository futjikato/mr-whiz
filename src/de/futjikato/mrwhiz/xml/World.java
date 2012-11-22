package de.futjikato.mrwhiz.xml;

public class World extends XmlObject {

	private Music backgroundMusic;

	@Override
	public void handleValue(String currentValue) throws ObjectNoValueSupport {
		throw new ObjectNoValueSupport();
	}

	@Override
	public void addChildObj(XmlObject mapObj) throws ObjectNoChildSupport, ObjectInvalidChild {
		if (mapObj instanceof Music) {
			this.backgroundMusic = (Music) mapObj;
			this.playBackgroundMusic();
		}
	}

	public void playBackgroundMusic() {
		this.backgroundMusic.play();
	}
}
