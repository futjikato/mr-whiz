package de.futjikato.mrwhiz.game;

import java.util.ArrayList;
import java.util.List;

public class Route {

	private List<List<Integer>> coords = new ArrayList<List<Integer>>();

	public void addCoords(List<Integer> cCoords) {
		coords.add(cCoords);
	}

	public void addCoords(List<Integer> cCoords, int index) {
		coords.set(index, cCoords);
	}

	public int getX(int index) {
		return coords.get(index).get(0);
	}

	public int getY(int index) {
		return coords.get(index).get(1);
	}

	public int getNextIndex(int index) {
		if (coords.size() - 1 > index) {
			return ++index;
		}
		return 0;
	}
}
