package de.futjikato.mrwhiz.xml;

import de.futjikato.mrwhiz.xml.attributes.Blocksize;
import de.futjikato.mrwhiz.xml.attributes.XmlAttribute;

public class Worldmap extends XmlObject {

	private Music backgroundMusic;

	/**
	 * Singleton storage for blocksize getter. use getBlocksize instead
	 */
	private Blocksize blocksize;

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

	public Blocksize getBlocksize() {
		if (this.blocksize == null) {
			XmlAttribute blockAttr = this.getAttribute("blocksize");

			if (blockAttr instanceof Blocksize) {
				this.blocksize = (Blocksize) blockAttr;
			}
		}

		return this.blocksize;
	}

	public void renderWorld(int bx, int by, int bw, int bh) {
		Blocksize blocksize = this.getBlocksize();

		// render all texture areas in viewport
		TextureAreaCollector areaCollector = TextureAreaCollector.getInstance();
		// TODO fix oofset after camera movemt on map is implemented
		areaCollector.drawBlocks(0, 0, bx, by, bw, bh, blocksize.getBlocksize());

		// TODO render decorations

		LevelCollector lvlCollect = LevelCollector.getInstance();
		lvlCollect.drawBlocks(bx, by, bw, bh, blocksize.getBlocksize());
	}
}
