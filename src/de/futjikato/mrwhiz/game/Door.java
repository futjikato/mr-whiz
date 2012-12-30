package de.futjikato.mrwhiz.game;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class Door extends Block {

	private boolean open = false;
	private Image openTexture;

	public Door(int bx, int by, char type, BlockDefinitions defines) {
		super(bx, by, type, defines);

		openTexture = defines.getTexture(type, "openTexture");
	}

	public void openDoor() {
		open = true;
		w = 2;
		cx = 28;
	}

	public void closeDoor() {
		open = false;
	}

	@Override
	public boolean hasCollision() {
		return !open;
	}

	@Override
	public void draw(float vpx, float vpy, int blocksize) {

		if (!doRender)
			return;

		Image img;
		if (open) {
			img = openTexture;
		} else {
			img = texture;
		}
		Graphics gra = new Graphics();

		int abX = (cx * blocksize) - (int) vpx - (int) xOffset;
		int abY = (cy * blocksize) - (int) vpy - (int) yOffset;
		int bw = blocksize * w;
		int bh = blocksize * h;

		gra.drawImage(img, abX, abY, abX + bw, abY + bh, 0, 0, img.getWidth(), img.getHeight());
	}
}
