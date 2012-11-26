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
	private SpriteSheet glSprite;
	private int sprintIndex = 0;

	private boolean longJump = false;
	private boolean longJumpPossible = true;

	public GamePlayer(float spawnx, float spawny) {
		this.x = spawnx;
		this.y = spawny;
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
		this.y = this.calcY(this.y, delta);

		if (input.isKeyDown(Input.KEY_D)) {
			this.x += 0.1f * delta;
			this.sprintIndex = 1;
		}

		if (input.isKeyDown(Input.KEY_A)) {
			this.x -= 0.1f * delta;
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
}