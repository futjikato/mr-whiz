package de.futjikato.mrwhiz.game;

import java.util.Collection;
import java.util.List;

import de.futjikato.mrwhiz.rendering.Boundary;
import de.futjikato.mrwhiz.rendering.Structure;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.Input;

import de.futjikato.mrwhiz.rendering.Renderer;

public final class GameRenderer extends Renderer {
	private Map map;
	private GameUi ui;
	private GamePlayer player;
    private Boundary bound;

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

		// init ui
		this.ui = new GameUi(this.player);
	}

	@Override
	protected void renderScene(long delta) {
        Collection<Structure> renderStructures = map.storage.getStructures(bound);

        for(Structure struc : renderStructures) {
            struc.render(bound);
        }
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

	}

    public void startMap(Map map) {
        this.map = map;

        // create boundary
        bound = new Boundary(map, 0, 0, Display.getWidth(), Display.getHeight());

        start();
    }
}
