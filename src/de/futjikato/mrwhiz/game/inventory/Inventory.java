package de.futjikato.mrwhiz.game.inventory;

import java.util.ArrayList;
import java.util.List;

import de.futjikato.mrwhiz.game.GamePlayer;

public class Inventory {

	private GamePlayer player;

	private List<Tool> inventory = new ArrayList<Tool>();

	public Inventory(GamePlayer player) {
		this.player = player;
	}

	public void addItem(Tool item) {
		inventory.add(item);
	}

	public List<Tool> getInventory() {
		return inventory;
	}
}