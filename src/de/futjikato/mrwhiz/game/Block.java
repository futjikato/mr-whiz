package de.futjikato.mrwhiz.game;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import de.futjikato.mrwhiz.game.ai.Dog;
import de.futjikato.mrwhiz.game.ai.NpcManager;
import de.futjikato.mrwhiz.xml.Gamemap;
import de.futjikato.mrwhiz.xml.Trigger;

public class Block {

	private Image texture;

	private List<Integer> xPos = new ArrayList<Integer>();

	private List<Integer> yPos = new ArrayList<Integer>();

	private int cx;

	private int cy;

	private int w;

	private int h;

	protected List<Trigger> touchListener = new ArrayList<Trigger>();

	private float speed;

	private int dmg;

	private boolean doRender = false;

	private char type;

	public Block(int bx, int by, char type, BlockDefinitions defines) {
		cx = bx;
		cy = by;

		h = defines.getBlockAttributeAsInt(type, "height", 1);
		w = defines.getBlockAttributeAsInt(type, "width", 1);

		this.type = type;
		doRender = defines.getBlockAttributeAsBoolean(type, "render", true);

		if (doRender) {
			this.texture = defines.getTexture(type);
			if (texture == null) {
				// fallback behavior if no texture is applied
				System.out.println(String.format("Block (%d/%d) should be rendered but has no texture !", cx, cy));
				doRender = false;
			}
		}

		String special = defines.getBlockAttributeAsString(type, "special");
		if (special != null) {
			// TODO create enum for block specials
			if (special.equals("PlayerSpawn")) {
				// TODO fix blocksize getter on GameRenderer
				int bs = 50;
				Gamemap.getInstance().setMapSpawnX(bx * bs);
				Gamemap.getInstance().setMapSpawnY(by * bs);
			}

			if (special.equals("NpcSpawn")) {
				String npcClassName = defines.getBlockAttributeAsString(type, "npc");
				// TODO create enum for NPC classes
				if (npcClassName.equals("Dog")) {
					// TODO fix blocksize getter on GameRenderer
					int bs = 50;

					Dog cDog = new Dog();
					cDog.spawn(bx * bs, by * bs);
					NpcManager.getInstance().add(cDog);
				}
			}
		}

		// load block damage from type
		dmg = defines.getBlockAttributeAsInt(type, "damage", 0);
	}

	public void draw(float vpx, float vpy, int blocksize) {

		if (!doRender)
			return;

		Image img = this.texture;
		Graphics gra = new Graphics();

		int abX = (cx * blocksize) - (int) vpx;
		int abY = (cy * blocksize) - (int) vpy;
		int bw = blocksize * w;
		int bh = blocksize * h;

		gra.drawImage(img, abX, abY, abX + bw, abY + bh, 0, 0, img.getWidth(), img.getHeight());
	}

	public int getDamage() {
		return dmg;
	}

	public float getSpeed() {
		return speed;
	}

	public void setDamage(int value) {
		dmg = value;
	}

	public void setSpeed(float value) {
		speed = value;
	}

	public boolean doRender() {
		return doRender;
	}

	public void setDoRender(boolean flag) {
		doRender = flag;
	}

	public void setX(int newX) {
		xPos.add(cx);
		cx = newX;
	}

	public void setY(int newY) {
		yPos.add(cy);
		cy = newY;
	}

	public int getX() {
		return cx;
	}

	public int getY() {
		return cy;
	}

	public char getType() {
		return type;
	}

	public void rollback(int steps) throws HistoryOutOfBoundsException {
		if (steps <= 0) {
			throw new HistoryOutOfBoundsException("negative step value");
		}

		if (steps > yPos.size()) {
			throw new HistoryOutOfBoundsException("step value out of history");
		}

		cy = yPos.get(yPos.size() - steps);
		yPos = yPos.subList(0, yPos.size() - steps);

		cx = xPos.get(xPos.size() - steps);
		xPos = xPos.subList(0, xPos.size() - steps);
	}

	public void rollback() throws HistoryOutOfBoundsException {
		rollback(yPos.size());
	}

	public void addTouchListener(Trigger caller) {
		this.touchListener.add(caller);
	}

	public void triggerTouch() {
		for ( Trigger trigger : this.touchListener ) {
			trigger.trigger();
		}
	}
}
