package de.futjikato.mrwhiz.game.ai;

import de.futjikato.mrwhiz.game.Block;
import de.futjikato.mrwhiz.game.GamePhysicalObject;

public abstract class Npc extends GamePhysicalObject {

	protected int health = 100;
	protected boolean isAlive;

	protected int getHealth() {
		return health;
	}

	protected void setHealth(int health) {
		this.health = health;
	}

	public void spawn(float x, float y) {
		setX(x);
		setY(y);

		isAlive = true;

		setGrip(1.2f);
		setMaxYVal(1.5f);
	}

	protected void hitBlock(Block block) {
		int blockDmg = block.getDamage();
		if (blockDmg > 0) {
			health -= blockDmg;
			if (health < 0)
				health = 0;
			if (health == 0)
				die();
		}
	}

	protected void die() {
		isAlive = false;
	}

	public void invoke(float vpx, float vpy, int blocksize, long delta) {
		if (isAlive) {
			this.nextStep(blocksize, delta);
			this.draw(vpx, vpy);
		}
	}

	public abstract void draw(float vpx, float vpy);

	protected abstract void nextStep(int blocksize, long delta);
}
