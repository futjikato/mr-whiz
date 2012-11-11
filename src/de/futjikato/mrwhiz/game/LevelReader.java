package de.futjikato.mrwhiz.game;

import de.futjikato.mrwhiz.game.objects.*;

public class LevelReader {
	
	private Background bg;
	
	public LevelReader(String mapname) {
		
		//TODO read map from some file and parse the content
		
		// dummy implementation
		this.bg = new Background();
	}
	
	public Background getBackground() {
		return this.bg;
	}
}
