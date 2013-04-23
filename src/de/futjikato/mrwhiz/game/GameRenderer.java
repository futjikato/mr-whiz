package de.futjikato.mrwhiz.game;

import java.util.Collection;
import java.util.List;

import de.futjikato.mrwhiz.rendering.*;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;

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

        this.player = new GamePlayer(0, 0, 50);

		// init ui
		this.ui = new GameUi(this.player);
	}

	@Override
	protected void renderScene(long delta) throws RenderException {
        int blocksize = Integer.valueOf(map.getConfigVar("blocksize", "50"));

        for(Coordinate coord : bound) {
            // get the structure
            Structure struc = map.storage.getStructure(coord.getX(), coord.getY());

            // skip if not existing or should not be rendered
            if(struc == null || !struc.doRender())
                continue;

            Image texture = struc.getTexture();
            if(texture == null)
                throw new RenderException("Renderable structures must have a texture.");

            // calculate rendering location on screen
            int realX = (coord.getX() - bound.getX()) * blocksize;
            int realY = (coord.getY() - bound.getY()) * blocksize;
            texture.draw(realX, realY, texture.getWidth(), texture.getHeight());
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
        bound = new Boundary(map, 20, 180, Display.getWidth(), Display.getHeight());

        start();
    }
}
