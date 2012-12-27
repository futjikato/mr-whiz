package de.futjikato.mrwhiz.game.inventory;

import java.util.ArrayList;
import java.util.List;

import de.futjikato.mrwhiz.game.GamePlayer;

public class Inventory {

	private GamePlayer player;

	private List<Tool> inventory = new ArrayList<Tool>();

	private List<Tool> removeList = new ArrayList<Tool>();

	public Inventory(GamePlayer player) {
		this.player = player;
	}

	public void addItem(Tool item) {
		inventory.add(item);
	}

	public List<Tool> getInventory() {

		if (removeList.size() > 0) {
			for ( Tool cTool : removeList ) {
				if (inventory.contains(cTool)) {
					inventory.remove(cTool);
				}
			}
		}

		return inventory;
	}

	public void setItemRemoveFlag(Tool item) {
		removeList.add(item);
	}
}