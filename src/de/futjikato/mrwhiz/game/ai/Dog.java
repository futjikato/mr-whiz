package de.futjikato.mrwhiz.game.ai;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public final class Dog extends Enemy {

	private static SpriteSheet sprite;

	static {
		try {
			sprite = new SpriteSheet("resources/images/npc/dog.png", 129, 90);
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void nextStep(int blocksize, long delta) {
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
		Image img = sprite.getSprite(0, 0);
		img.draw(this.getX() - vpx, this.getY() - vpy - img.getHeight());
	}
}
