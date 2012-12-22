package de.futjikato.mrwhiz.game;

import java.util.List;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.Input;

import de.futjikato.mrwhiz.App;
import de.futjikato.mrwhiz.Renderer;
import de.futjikato.mrwhiz.game.ai.NpcManager;
import de.futjikato.mrwhiz.xml.Block;
import de.futjikato.mrwhiz.xml.BlockCollector;
import de.futjikato.mrwhiz.xml.Gamemap;
import de.futjikato.mrwhiz.xml.Item;
import de.futjikato.mrwhiz.xml.ItemCollector;
import de.futjikato.mrwhiz.xml.LevelCollector;
import de.futjikato.mrwhiz.xml.TextureAreaCollector;
import de.futjikato.mrwhiz.xml.XmlReader;
import de.futjikato.mrwhiz.xml.attributes.Spawn;

public final class GameRenderer extends Renderer {
	private Gamemap map;
	private GameUi ui;
	private GamePlayer player;

	private float viewPortX = 0.0f;
	private float viewPortY = 0.0f;
	private int viewPortxb = 0;
	private int viewPortyb = 0;
	private int viewPortwb;
	private int viewPorthb;

	private static GameRenderer instance;

	public static GameRenderer getInstance() {
		if (null == instance) {
			instance = new GameRenderer();
		}
		return instance;
	}

	@Override
	protected void init() throws LWJGLException {
		super.init();

		// get xml mapreader from gamemap
		XmlReader reader = App.getInstance().getNextGameMap().getReader();
		this.map = reader.getGame();

		// calculate viewport block with & height
		this.viewPortwb = (int) Math.ceil(Display.getWidth() / (double) this.map.getBlocksize().getValue()) + 1;
		this.viewPorthb = (int) Math.ceil(Display.getHeight() / (double) this.map.getBlocksize().getValue()) + 1;

		// init player
		Spawn spawn = this.map.getSpawn();
		this.player = new GamePlayer(spawn.getX(), spawn.getY(), this.map.getBlocksize().getValue());

		// init ui
		this.ui = new GameUi(this.player);
	}

	@Override
	protected void renderScene(long delta) {
		// invoke event trigger class
		GameTimeTrigger.getInstance().update();

		// calc new screen position
		this.calcNewScreenViewportPosition();

		// render all texture areas in viewport
		TextureAreaCollector areaCollector = TextureAreaCollector.getInstance();
		areaCollector.draw(this.viewPortX, this.viewPortY, this.map.getBlocksize().getValue());

		// render blocks
		List<Block> blocks = BlockCollector.getInstance().getBlocksByBlockCoords(this.viewPortxb, this.viewPortyb, this.viewPortwb, this.viewPorthb, this.map.getBlocksize().getBlocksize());
		for ( Block block : blocks ) {
			block.draw(this.viewPortX, this.viewPortY, this.map.getBlocksize().getValue());
		}

		// render items
		List<Item> items = ItemCollector.getInstance().getItemsByBlockCoords(this.viewPortxb, this.viewPortyb, this.viewPortwb, this.viewPorthb, this.map.getBlocksize().getBlocksize());
		for ( Item item : items ) {
			item.draw(this.viewPortX, this.viewPortY, this.map.getBlocksize().getValue());
		}

		// invoke all npc´s
		NpcManager.getInstance().invokeAll(this.viewPortX, this.viewPortY, this.map.getBlocksize().getValue(), delta);

		this.player.render(this.viewPortX, this.viewPortY);
	}

	@Override
	protected void renderUi(long delta) {
		this.ui.update();
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
		this.ui.setFps(fps);
	}

	@Override
	protected void clean() {
		LevelCollector.getInstance().clean();
		TextureAreaCollector.getInstance().clean();
	}

	private void calcNewScreenViewportPosition() {
		float playerX = this.player.getX();
		float playerY = this.player.getY();

		this.viewPortX = playerX - (Display.getWidth() / 2) - (this.player.getWidth() / 2);
		this.viewPortY = playerY - (Display.getHeight() / 2) - (this.player.getHeight() / 2);

		// TODO check if we can center camera or if we need to add/sub an offset

		this.viewPortxb = (int) Math.floor(this.viewPortX / this.map.getBlocksize().getValue());
		this.viewPortyb = (int) Math.floor(this.viewPortY / this.map.getBlocksize().getValue());
	}

	public int getBlocksize() {
		return this.map.getBlocksize().getValue();
	}
}
