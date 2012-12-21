package de.futjikato.mrwhiz.game.ai;

import java.util.ArrayList;
import java.util.List;


public class NpcManager {

	private static NpcManager instance;

	private List<Npc> list;

	private NpcManager() {
		list = new ArrayList<Npc>();
	}

	public static NpcManager getInstance() {
		if (null == instance) {
			instance = new NpcManager();
		}
		return instance;
	}

	public void invokeAll() {
		for ( Npc npc : list ) {
			npc.invoke();
		}
	}

	public void add(Npc npc) {
		list.add(npc);
	}
}
