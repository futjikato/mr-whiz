package de.futjikato.mrwhiz.game;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class GamePlayer {
	private static final int PLAYER_WIDTH = 80;
	private static final int PLAYER_HEIGHT = 149;

	private int y;
	private int x;
	private SpriteSheet glSprite;

	public GamePlayer(int spawnx, int spawny) {
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
		Image tile = sprite.getSprite(0, 0);

		// TODO this could eventually be improved a bit ;-)
		tile.draw(this.x - (GamePlayer.PLAYER_WIDTH / 2), this.y - GamePlayer.PLAYER_HEIGHT);
	}
}