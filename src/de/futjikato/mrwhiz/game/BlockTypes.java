package de.futjikato.mrwhiz.game;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import de.futjikato.mrwhiz.xml.Gamemap;

public enum BlockTypes {
	G() {
		@Override
		public Image getTexture() {
			try {
				return new Image("resources/images/backgrounds/game_grass_ground.png");
			} catch (SlickException e) {
				e.printStackTrace();
			}
			return null;
		}
	},

	D() {
		@Override
		public Image getTexture() {
			try {
				return new Image("resources/images/backgrounds/game_dirt.png");
			} catch (SlickException e) {
				e.printStackTrace();
			}
			return null;
		}
	},

	B() {
		@Override
		public Image getTexture() {
			try {
				return new Image("resources/images/backgrounds/game_brick.png");
			} catch (SlickException e) {
				e.printStackTrace();
			}
			return null;
		}
	},

	P() {
		@Override
		public boolean doRender() {
			return false;
		}

		@Override
		public void informGame(Block self) {
			// int bs = GameRenderer.getInstance().getBlocksize();
			int bs = 50;

			int absX = self.getX() * bs;
			int absY = self.getY() * bs;

			Gamemap.getInstance().setMapSpawnX((float) absX);
			Gamemap.getInstance().setMapSpawnY((float) absY);
		}
	},

	t() {
		@Override
		public Image getTexture() {
			try {
				return new Image("resources/images/backgrounds/game_thorn.png");
			} catch (SlickException e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		public int getDamage() {
			return 20;
		}
	};

	public Image getTexture() {
		return null;
	}

	public boolean doRender() {
		return true;
	}

	public void informGame(Block self) {
		// do nothing in default implememtation
	}

	public int getDamage() {
		return 0;
	}
}
