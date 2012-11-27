package de.futjikato.mrwhiz.game;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class GamePlayer extends GamePhysicalObject {
	private static final int PLAYER_WIDTH = 80;
	private static final int PLAYER_HEIGHT = 149;

	private int blocksize;
	private SpriteSheet glSprite;
	private int sprintIndex = 0;

	private boolean longJump = false;
	private boolean jumpKeyPressed = true;

	public GamePlayer(float spawnx, float spawny, int blocksize) {
		this.setX(spawnx);
		this.setY(spawny);
		this.blocksize = blocksize;

		this.setGrip(1.2f);
		this.setMaxYVal(1.5f);
	}

	private SpriteSheet getSprite() {
		if (this.glSprite == null) {
			try {
				this.glSprite = new SpriteSheet("resources/images/player_game.png", 80, 149, 1);
			} catch (SlickException e) {
				e.printStackTrace();
			}
		}

		return this.glSprite;
	}

	public void render(float vpx, float vpy) {
		SpriteSheet sprite = this.getSprite();
		Image tile = sprite.getSprite(0, this.sprintIndex);

		// TODO this could eventually be improved a bit ;-)
		tile.draw(this.getX() - vpx - (GamePlayer.PLAYER_WIDTH / 2), this.getY() - vpy - GamePlayer.PLAYER_HEIGHT);
	}

	public void handleInput(long delta, Input input) {
		// get new y position
		this.calcNewPos(this.getX(), this.getY(), this.blocksize, delta);

		if (input.isKeyDown(Input.KEY_D)) {
			this.setXvel(0.5f);
			this.sprintIndex = 1;
		}

		if (input.isKeyDown(Input.KEY_A)) {
			this.setXvel(-0.5f);
			this.sprintIndex = 2;
		}

		if (input.isKeyDown(Input.KEY_SPACE)) {
			this.jump();
		} else {
			this.jumpKeyPressed = false;
		}
	}

	public void jump() {
		if (this.getYVel() == 0) {
			this.setYVel(-1.2f);
			this.longJump = false;
			this.jumpKeyPressed = true;
		} else if (this.getYVel() > -0.7 && this.getYVel() < -0.5 && this.jumpKeyPressed && !this.longJump) {
			this.setYVel(-0.9f);
			this.longJump = true;
		}
	}

	@Override
	protected int getHeight() {
		return PLAYER_HEIGHT;
	}

	@Override
	protected int getWidth() {
		return PLAYER_WIDTH;
	}
}