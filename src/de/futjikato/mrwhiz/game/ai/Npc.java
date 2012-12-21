package de.futjikato.mrwhiz.game.ai;

import de.futjikato.mrwhiz.game.GamePhysicalObject;
import de.futjikato.mrwhiz.xml.Block;

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

	public void invoke() {
		if (isAlive) {
			this.nextStep();
			this.draw();
		}
	}

	public abstract void draw();

	protected abstract void nextStep();
}
