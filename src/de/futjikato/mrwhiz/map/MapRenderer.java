package de.futjikato.mrwhiz.map;

import org.lwjgl.LWJGLException;
import org.newdawn.slick.Input;

import de.futjikato.mrwhiz.Renderer;

public class MapRenderer extends Renderer {

	private MapReader map;
	
	@Override
	protected void init() throws LWJGLException {
		super.init();
		
		this.map = new MapReader("resources/data/worldmap.xml");
	}
	
	@Override
	protected void renderScene(long delta) {
		if(!this.map.mapReady()) {
			return;
		}
	}

	@Override
	protected void renderUi(long delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void handleInput(long delta, Input input) {
		// TODO Auto-generated method stub
		
	}

}
