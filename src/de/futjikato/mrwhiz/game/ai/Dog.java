package de.futjikato.mrwhiz.game.ai;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import de.futjikato.mrwhiz.xml.Block;

public final class Dog extends Enemy {

	private static SpriteSheet sprite;

	private float currSpeed = -0.2f;

	static {
		try {
			sprite = new SpriteSheet("resources/images/npc/dog.png", 129, 89, 1);
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void nextStep(int blocksize, long delta) {

		setXvel(currSpeed);

		this.calcNewPos(blocksize, delta);
	}

	@Override
	protected int getHeight() {
		return 90;
	}

	@Override
	protected int getWidth() {
		return 129;
	}

	@Override
	public void draw(float vpx, float vpy) {

		int spriteIndex = 0;
		if (currSpeed > 0)
			spriteIndex = 1;

		Image img = sprite.getSprite(spriteIndex, 0);
		img.draw(this.getX() - vpx, this.getY() - vpy - img.getHeight());
	}

	@Override
	protected void hitBlock(Block block, String direction) {
		super.hitBlock(block);

		if (direction.equals("x")) {
			if (currSpeed > 0) {
				currSpeed = -0.2f;
			} else {
				currSpeed = 0.2f;
			}
		}
	}
}
