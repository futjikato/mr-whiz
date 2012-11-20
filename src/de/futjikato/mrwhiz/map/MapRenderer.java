package de.futjikato.mrwhiz.map;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.Input;

import de.futjikato.mrwhiz.Renderer;
import de.futjikato.mrwhiz.xml.TextureAreaCollector;
import de.futjikato.mrwhiz.xml.World;
import de.futjikato.mrwhiz.xml.attributes.Spawn;
import de.futjikato.mrwhiz.xml.attributes.XmlAttribute;

public class MapRenderer extends Renderer {

	private MapReader map;
	private MapPlayer player;
	
	public static final int BLOCKSIZE = 50;
	
	private int viewPortxb = 0;
	private int viewPortyb = 0;
	private int viewPortwb;
	private int viewPorthb;
	
	@Override
	protected void init() throws LWJGLException {
		super.init();
		
		this.map = new MapReader("resources/data/worldmap.xml");
		
		// read spawn point 
		World world = this.map.getWorld();
		XmlAttribute attr = world.getAttribute("spawn");
		
		int spawnX = 50;
		int spawnY = 50;
		
		if(attr != null && attr instanceof Spawn) {
			Spawn spawn = (Spawn) attr;
			
			spawnX = spawn.getX();
			spawnY = spawn.getY();
		}
		
		// spawn player
		this.player = new MapPlayer(spawnX, spawnY);
		
		// calculate viewport block with & height
		this.viewPortwb = Display.getWidth() / MapRenderer.BLOCKSIZE;
		this.viewPorthb = Display.getHeight() / MapRenderer.BLOCKSIZE;
	}
	
	@Override
	protected void renderScene(long delta) {
		// get world object
		World world = this.map.getWorld();
		
		if(world == null) {
			return;
		}
		
		// render all texture areas in viewport
		TextureAreaCollector areaCollector = TextureAreaCollector.getInstance();
		areaCollector.drawBlocks(this.viewPortxb, this.viewPortyb, this.viewPortwb, this.viewPorthb);
		
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
