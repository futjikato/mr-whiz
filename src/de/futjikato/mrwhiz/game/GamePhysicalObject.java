package de.futjikato.mrwhiz.game;

import org.lwjgl.opengl.Display;

import de.futjikato.mrwhiz.Physical;

public abstract class GamePhysicalObject extends Physical {

	private boolean still = true;

	protected boolean isStill() {
		return this.still;
	}

	@Override
	protected boolean checkCollide(float y) {
		// TODO instead of floor check where blocks are
		boolean retVal = y < Display.getHeight();

		// save still state
		this.still = !retVal;

		return retVal;
	}
}
