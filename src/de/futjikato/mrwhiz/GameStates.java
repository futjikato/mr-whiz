package de.futjikato.mrwhiz;

import de.futjikato.mrwhiz.game.GameRenderer;
import de.futjikato.mrwhiz.map.MapRenderer;

public enum GameStates {
	QUIT(){ protected Renderer getRenderer(){ return null; } },
	MENU(){ protected Renderer getRenderer(){ return null; } }, 
	WORLDMAP(){ protected Renderer getRenderer(){ return new MapRenderer(); } },
	GAME(){ protected Renderer getRenderer(){ return new GameRenderer(); } };
	
	private GameStates exitState;
	private Renderer renderer;
	
	static {
		MENU.exitState = QUIT;
		WORLDMAP.exitState = MENU;
		GAME.exitState = WORLDMAP;
	}
	
	protected GameStates getExistState() {
		return this.exitState;
	}
	
	protected abstract Renderer getRenderer();
	
	public void start() {
		this.renderer = this.getRenderer();
		
		// prevent quit state from calling start
		if(this.renderer != null) {
			this.renderer.start();
		}
	}
	
	public void stop() {
		this.renderer.stop();
	}
}
