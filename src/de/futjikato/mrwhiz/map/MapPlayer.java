package de.futjikato.mrwhiz.map;

import de.futjikato.mrwhiz.Renderable;

public class MapPlayer implements Renderable {
	
	private int x;
	private int y;
	
	public MapPlayer(int spawnX, int spawnY) {
		this.x = spawnX;
		this.y = spawnY;
	}

	@Override
	public void draw() {
		
	}
}
