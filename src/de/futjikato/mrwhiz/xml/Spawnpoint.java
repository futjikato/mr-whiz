package de.futjikato.mrwhiz.xml;

import de.futjikato.mrwhiz.game.ai.Dog;
import de.futjikato.mrwhiz.game.ai.Npc;
import de.futjikato.mrwhiz.game.ai.NpcManager;
import de.futjikato.mrwhiz.xml.attributes.Dimensions;

public class Spawnpoint extends XmlObject {

	private enum Names {

		dog() {
			@Override
			protected Npc getNew() {
				return new Dog();
			}
		};

		protected abstract Npc getNew();
	}

	private String npcType;

	@Override
	public void handleValue(String currentValue) throws ObjectNoValueSupport {
		this.npcType = currentValue;
	}

	@Override
	public void addChildObj(XmlObject mapObj) throws ObjectNoChildSupport, ObjectInvalidChild {
		throw new ObjectNoChildSupport();
	}

	public void triggerSpawn() {
		Dimensions ownDim = this.getDimensions();

		Npc newNpc = Names.valueOf(this.npcType).getNew();
		newNpc.spawn(ownDim.getAbsX(), ownDim.getAbsY());
		NpcManager.getInstance().add(newNpc);
	}
}
