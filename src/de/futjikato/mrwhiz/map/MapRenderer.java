package de.futjikato.mrwhiz.map;

import java.util.Stack;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.Input;

import de.futjikato.mrwhiz.Renderer;
import de.futjikato.mrwhiz.xml.TextureArea;
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
		
		// render all texture areas
		//TODO render only texture areas in viewport
		Stack<TextureArea> areas = world.getTextureAreas();
		for(TextureArea area : areas) {
			area.draw();
		}
		
		//TODO load and render all decorations
		
		//TODo load and render all level entrys
	}

	@Override
	protected void renderUi(long delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void handleInput(long delta, Input input) {
		if(Display.isCloseRequested()) {
			this.isStoped = true;
		}
	}

}
