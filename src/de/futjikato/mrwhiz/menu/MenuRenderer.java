package de.futjikato.mrwhiz.menu;

import org.lwjgl.LWJGLException;
import org.newdawn.slick.Input;

import de.futjikato.mrwhiz.Renderer;

public class MenuRenderer extends Renderer {

	private MenuUi ui;

	@Override
	protected void init() throws LWJGLException {
		this.ui = new MenuUi();
	}

	@Override
	protected void clean() {
		// TODO clean up stuff you may needed for rendering the background
	}

	@Override
	protected void renderScene(long delta) {
		// TODO render some background for the menu here
	}

	@Override
	protected void renderUi(long delta) {
		this.ui.update();
	}

	@Override
	protected void handleInput(long delta, Input input) {
		if (this.ui.isStopRequested()) {
			this.stop();
		}
	}

	@Override
	protected void printFps(long fps) {
		// TODO Auto-generated method stub

	}

}
