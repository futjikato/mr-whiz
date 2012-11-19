package de.futjikato.mrwhiz.map;

import java.util.Stack;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.Input;

import de.futjikato.mrwhiz.Renderer;
import de.futjikato.mrwhiz.xml.TextureArea;
import de.futjikato.mrwhiz.xml.TextureAreaCollector;
import de.futjikato.mrwhiz.xml.World;

public class MapRenderer extends Renderer {

	private MapReader map;
	private MapPlayer player;
	
	private int viewPortxb = 0;
	private int viewPortyb = 0;
	private int viewPortwb;
	private int viewPorthb;
	
	@Override
	protected void init() throws LWJGLException {
		super.init();
		
		this.map = new MapReader("resources/data/worldmap.xml");
		
		this.player = new MapPlayer(20, 20);
		
		this.viewPortwb = Display.getWidth() / 50;
		this.viewPorthb = Display.getHeight() / 50;
	}
	
	@Override
	protected void renderScene(long delta) {
		// get world object
		World world = this.map.getWorld();
		
		if(world == null) {
			return;
		}
		
		// render all texture areas
		TextureAreaCollector areaCollector = TextureAreaCollector.getInstance();
		Stack<TextureArea> areas = areaCollector.getAreas(this.viewPortxb, this.viewPortyb, this.viewPortwb, this.viewPorthb);
		for(TextureArea area : areas) {
			area.draw();
		}
		
		//TODO render decorations
		
		//TODO load and render all level entrys
		
		// render player
		this.player.draw();
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
		
		this.player.handleInput(delta, input);
	}

}
