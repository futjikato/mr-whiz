package de.futjikato.mrwhiz.rendering;

import de.futjikato.mrwhiz.App;
import de.futjikato.mrwhiz.Util;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Input;

public abstract class Renderer {

	protected boolean isStoped;

	public void start() {

		// stoped to false
		this.isStoped = false;

		try {
			this.renderLoop();
		} catch (Exception e) {
			this.isStoped = true;

			e.printStackTrace();
		}
	}

	public void stop() {
		this.isStoped = true;
	}

	private void renderLoop() throws Exception {
		this.init();
		this.initFPS();

		while (!this.isStoped) {
			// clear
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
			GL11.glLoadIdentity();

			// render
			GL11.glMatrixMode(GL11.GL_PROJECTION);
			GL11.glLoadIdentity();
			GL11.glOrtho(0, Display.getDisplayMode().getWidth(), Display.getDisplayMode().getHeight(), 0, -1, 1);
			GL11.glMatrixMode(GL11.GL_MODELVIEW);

			// enable textures
			GL11.glEnable(GL11.GL_TEXTURE_2D);
			// enable alpha blending
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

			long delta = this.getDelta();

			this.handleInput(delta, App.getInstance().getInput());
			this.renderScene(delta);
			this.renderUi(delta);

			// update screen
			Display.update();

			this.updateFPS();
		}

		this.clean();
	}

	protected abstract void clean();

	protected abstract void renderScene(long delta) throws RenderException;

	protected abstract void renderUi(long delta);

	protected abstract void handleInput(long delta, Input input);

	/**
	 * ++++++++++++++++++++++++++++++++++++++++++++
     * FPS calculation and stuff
	 * ++++++++++++++++++++++++++++++++++++++++++++
	 */

	protected long lastFrame;
	protected long lastFPS;
	protected long fps;

	/**
	 * Calculate how many milliseconds have passed since last frame.
	 * 
	 * @return milliseconds passed since last frame
	 */
	public int getDelta() {
		long time = Util.getTime();
		int delta = (int) (time - this.lastFrame);
		this.lastFrame = Util.getTime();

		return delta;
	}

	protected abstract void printFps(long fps);

	/**
	 * Calculate the FPS and set it in the title bar
	 */
	private void updateFPS() {
		if (Util.getTime() - this.lastFPS > 1000) {
			this.printFps(this.fps);
			this.fps = 0; // reset the FPS counter
			this.lastFPS += 1000; // add one second
		}
		fps++;
	}

	private void initFPS() {
		// init fps variables
		this.lastFrame = Util.getTime();
		this.lastFPS = Util.getTime();
		this.fps = 0;
	}

	protected void init() throws LWJGLException {
	}
}
