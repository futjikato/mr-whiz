package de.futjikato.mrwhiz.game;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class GamePlayer extends GamePhysicalObject {
	private static final int PLAYER_WIDTH = 80;
	private static final int PLAYER_HEIGHT = 149;

	private float y;
	private float x;
	private int blocksize;
	private SpriteSheet glSprite;
	private int sprintIndex = 0;

	private boolean longJump = false;
	private boolean longJumpPossible = true;

	public GamePlayer(float spawnx, float spawny, int blocksize) {
		this.x = spawnx;
		this.y = spawny;
		this.blocksize = blocksize;

		this.setGrip(1.2f);
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

	public void render() {
		SpriteSheet sprite = this.getSprite();
		Image tile = sprite.getSprite(0, this.sprintIndex);

		// TODO this could eventually be improved a bit ;-)
		tile.draw(this.x - (GamePlayer.PLAYER_WIDTH / 2), this.y - GamePlayer.PLAYER_HEIGHT);
	}

	public void handleInput(long delta, Input input) {
		// get new y position
		float[] newPositions = this.calcNewPos(this.x, this.y, this.blocksize, delta);
		this.x = newPositions[0];
		this.y = newPositions[1];

		if (input.isKeyDown(Input.KEY_D)) {
			this.setXvel(1);
			this.sprintIndex = 1;
		}

		if (input.isKeyDown(Input.KEY_A)) {
			this.setXvel(-1);
			this.sprintIndex = 2;
		}
	}

	public void jump() {
		if (this.isStill()) {
			this.setYVel(-2.5f);
			this.longJumpPossible = true;
			this.longJump = false;
		} else if (this.getYVel() > -1 && this.longJumpPossible && !this.longJump) {
			this.setYVel(-2.2f);
			this.longJump = true;
		}
	}

	public void stopJump() {
		this.longJumpPossible = false;
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