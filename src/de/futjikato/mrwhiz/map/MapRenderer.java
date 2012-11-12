package de.futjikato.mrwhiz.map;

import org.lwjgl.LWJGLException;
import org.newdawn.slick.Input;

import de.futjikato.mrwhiz.Renderer;
import de.futjikato.mrwhiz.xml.World;

public class MapRenderer extends Renderer {

	private MapReader map;
	
	@Override
	protected void init() throws LWJGLException {
		super.init();
		
		this.map = new MapReader("resources/data/worldmap.xml");
	}
	
	@Override
	protected void renderScene(long delta) {
		// get world object
		World world = this.map.getWorld();
		
		if(world == null) {
			return;
		}
		
		//TODO render world
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
