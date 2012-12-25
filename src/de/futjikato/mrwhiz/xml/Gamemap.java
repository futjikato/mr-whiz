package de.futjikato.mrwhiz.xml;

import de.futjikato.mrwhiz.xml.attributes.Blocksize;
import de.futjikato.mrwhiz.xml.attributes.Spawn;
import de.futjikato.mrwhiz.xml.attributes.XmlAttribute;

public class Gamemap extends XmlObject {

	private Blocksize blocksize;

	private static Gamemap instance;

	private float spawnX;

	private float spawnY;

	private Gamemap() {
	}

	public static Gamemap getInstance() {
		if (null == instance) {
			instance = new Gamemap();
		}
		return instance;
	}

	@Override
	public void handleValue(String currentValue) throws ObjectNoValueSupport {
		throw new ObjectNoValueSupport();
	}

	@Override
	public void addChildObj(XmlObject mapObj) throws ObjectNoChildSupport, ObjectInvalidChild {
		// TODO Auto-generated method stub

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

	@Deprecated
	public Spawn getSpawn() {
		XmlAttribute xmlAttr = this.getAttribute("spawn");
		if (xmlAttr instanceof Spawn) {
			return (Spawn) xmlAttr;
		}
		return null;
	}

	public void setMapSpawnX(float x) {
		spawnX = x;
	}

	public void setMapSpawnY(float y) {
		spawnY = y;
	}

	public float getMapSpawnX() {
		return spawnX;
	}

	public float getMapSpawnY() {
		return spawnY;
	}
}
