package de.futjikato.mrwhiz.game;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.Input;

import de.futjikato.mrwhiz.App;
import de.futjikato.mrwhiz.Renderer;
import de.futjikato.mrwhiz.xml.LevelCollector;
import de.futjikato.mrwhiz.xml.TextureAreaCollector;
import de.futjikato.mrwhiz.xml.World;
import de.futjikato.mrwhiz.xml.XmlReader;

public class GameRenderer extends Renderer {

	protected int width = 500;
	protected int height = 500;

	private XmlReader map;

	public static final int BLOCKSIZE = 80;

	private int viewPortxb = 0;
	private int viewPortyb = 0;
	private int viewPortwb;
	private int viewPorthb;

	@Override
	protected void init() throws LWJGLException {
		super.init();

		// get xml mapreader from gamemap
		this.map = App.getInstance().getNextGameMap().getReader();
	}

	@Override
	protected void renderScene(long delta) {

		// get world object
		World world = this.map.getWorld();

		if (world == null) {
			return;
		}

		// render all texture areas in viewport
		TextureAreaCollector areaCollector = TextureAreaCollector.getInstance();
		areaCollector.drawBlocks(this.viewPortxb, this.viewPortyb, this.viewPortwb, this.viewPorthb);
	}

	@Override
	protected void renderUi(long delta) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void handleInput(long delta, Input input) {
		if (Display.isCloseRequested()) {
			this.isStoped = true;
		}
	}

	@Override
	protected void printFps(long fps) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void clean() {
		LevelCollector.getInstance().clean();
		TextureAreaCollector.getInstance().clean();
	}
}
