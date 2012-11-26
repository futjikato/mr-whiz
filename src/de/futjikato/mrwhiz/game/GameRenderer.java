package de.futjikato.mrwhiz.game;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.Input;

import de.futjikato.mrwhiz.App;
import de.futjikato.mrwhiz.Renderer;
import de.futjikato.mrwhiz.xml.BlockCollector;
import de.futjikato.mrwhiz.xml.Gamemap;
import de.futjikato.mrwhiz.xml.LevelCollector;
import de.futjikato.mrwhiz.xml.TextureAreaCollector;
import de.futjikato.mrwhiz.xml.XmlReader;

public final class GameRenderer extends Renderer {

	protected int width = 500;
	protected int height = 500;

	private Gamemap map;
	private GamePlayer player;

	public static final int BLOCKSIZE = 80;

	private int viewPortxb = 0;
	private int viewPortyb = 0;
	private int viewPortwb;
	private int viewPorthb;

	@Override
	protected void init() throws LWJGLException {
		super.init();

		// get xml mapreader from gamemap
		XmlReader reader = App.getInstance().getNextGameMap().getReader();
		this.map = reader.getGame();

		// calculate viewport block with & height
		this.viewPortwb = (int) Math.ceil(Display.getWidth() / (double) this.map.getBlocksize().getBlocksize());
		this.viewPorthb = (int) Math.ceil(Display.getHeight() / (double) this.map.getBlocksize().getBlocksize());

		// init player
		this.player = new GamePlayer(100, 200, this.map.getBlocksize().getBlocksize());
	}

	@Override
	protected void renderScene(long delta) {

		// render all texture areas in viewport
		TextureAreaCollector areaCollector = TextureAreaCollector.getInstance();
		areaCollector.drawBlocks(this.viewPortxb, this.viewPortyb, this.viewPortwb, this.viewPorthb, this.map.getBlocksize().getBlocksize());

		// render blocks
		BlockCollector.getInstance().drawBlocks(this.viewPortxb, this.viewPortyb, this.viewPortwb, this.viewPorthb, this.map.getBlocksize().getBlocksize());

		this.player.render();
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

		this.player.handleInput(delta, input);
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
