package de.futjikato.mrwhiz.game;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Input;

import de.futjikato.mrwhiz.Renderer;
import de.futjikato.mrwhiz.xml.LevelCollector;
import de.futjikato.mrwhiz.xml.TextureAreaCollector;

public class GameRenderer extends Renderer {

	protected int width = 500;
	protected int height = 500;

	private LevelReader map;

	@Override
	protected void init() throws LWJGLException {
		super.init();

		// TODO after implementing real mapreader this dummy input must be
		// replaced with something real
		this.map = new LevelReader("l01");
	}

	@Override
	protected void renderScene(long delta) {
		this.renderBackground();
		this.RenderPlayer();
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

	private void renderBackground() {
		map.getBackground().draw();
	}

	private void RenderPlayer() {
		// center player
		GL11.glPushMatrix();

		GL11.glTranslatef(Display.getDisplayMode().getWidth() / 2, 25, 0);
		GL11.glColor3f(0, 1, 0);

		GL11.glBegin(GL11.GL_QUADS);

		GL11.glVertex2i(-25, -50);
		GL11.glVertex2i(25, -50);
		GL11.glVertex2i(25, 50);
		GL11.glVertex2i(-25, 50);

		GL11.glEnd();

		GL11.glPopMatrix();
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
