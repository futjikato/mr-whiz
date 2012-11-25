package de.futjikato.mrwhiz.game;

import org.lwjgl.opengl.Display;

import de.futjikato.mrwhiz.Physical;

public abstract class GamePhysicalObject extends Physical {

	@Override
	protected boolean checkCollide(float y) {
		// TODO instead of floor check where blocks are
		return y < Display.getHeight();
	}
}
