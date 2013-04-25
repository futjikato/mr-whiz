package de.futjikato.mrwhiz.game;

import de.futjikato.mrwhiz.rendering.*;
import de.futjikato.mrwhiz.rendering.positioning.Coordinate;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

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

        this.player = new GamePlayer();

		// init ui
		this.ui = new GameUi(this.player);
	}

	@Override
	protected void renderScene(long delta) throws RenderException {
        int blocksize = Integer.valueOf(map.getConfigVar("blocksize", "50"));

        for(Coordinate<Integer> coord : bound) {
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

            // render texture according to texture mode
            String txtMode = struc.getTextureMode();
            if(txtMode.equals(Structure.OPT_TEXTUREMODE_FIT)) {
                texture.draw(realX, realY, realX + blocksize, realY + blocksize, 0, 0, texture.getWidth(), texture.getHeight());
            } else if(txtMode.equals(Structure.OPT_TEXTUREMODE_FULL)) {
                texture.draw(realX, realY, texture.getWidth(), texture.getHeight());
            } else if(txtMode.equals(Structure.OPT_TEXTUREMODE_CROP)) {
                texture.draw(realX, realY, realX + blocksize, realY + blocksize, 0, 0, blocksize, blocksize);
            } else {
                throw new RenderException(String.format("Unknown texture mode %s", txtMode));
            }
        }

        // render players
        try {
            Image playerTexture = player.getTexture();
        } catch (SlickException e) {
            throw new RenderException(e);
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
        int blocksize = Integer.valueOf(map.getConfigVar("blocksize", "50"));
        bound = new Boundary(map, blocksize * 1, blocksize * 189, Display.getWidth(), Display.getHeight());

        start();
    }
}
