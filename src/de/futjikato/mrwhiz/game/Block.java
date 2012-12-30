package de.futjikato.mrwhiz.game;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import de.futjikato.mrwhiz.game.ai.Dog;
import de.futjikato.mrwhiz.game.ai.NpcManager;
import de.futjikato.mrwhiz.xml.BlockCollector;
import de.futjikato.mrwhiz.xml.Gamemap;
import de.futjikato.mrwhiz.xml.Trigger;

public class Block {

	protected Image texture;

	private List<Integer> xPos = new ArrayList<Integer>();

	private List<Integer> yPos = new ArrayList<Integer>();

	protected int cx;

	protected int cy;

	protected int w;

	protected int h;

	protected float xOffset;

	protected float yOffset;

	protected List<Trigger> touchListener = new ArrayList<Trigger>();

	private float speed;

	private int dmg;

	protected boolean doRender = false;

	private char type;

	protected Route route;

	protected int routeIndex;

	protected boolean patroll = false;

	public Block(int bx, int by, char type, BlockDefinitions defines) {
		cx = bx;
		cy = by;

		h = defines.getBlockAttributeAsInt(type, "height", 1);
		w = defines.getBlockAttributeAsInt(type, "width", 1);

		xOffset = defines.getBlockAttributeAsInt(type, "xOffset", 0);
		yOffset = defines.getBlockAttributeAsInt(type, "yOffset", 0);

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

		if (patroll) {
			calcNewPosition();
		}

		Image img = this.texture;
		Graphics gra = new Graphics();

		int abX = (cx * blocksize) - (int) vpx - (int) xOffset;
		int abY = (cy * blocksize) - (int) vpy - (int) yOffset;
		int bw = blocksize * w;
		int bh = blocksize * h;

		gra.drawImage(img, abX, abY, abX + bw, abY + bh, 0, 0, img.getWidth(), img.getHeight());
	}

	private void calcNewPosition() {
		if (!patroll) {
			return;
		}

		// get position of next routepoint
		int finalBx = route.getX(routeIndex);
		int finalBy = route.getY(routeIndex);

		// check if we reached that point and get next one if so
		if (getX() == finalBx && xOffset == 0 && getY() == finalBy && yOffset == 0) {
			routeIndex = route.getNextIndex(routeIndex);

			finalBx = route.getX(routeIndex);
			finalBy = route.getY(routeIndex);
		}

		int blocksize = GameRenderer.getInstance().getBlocksize();

		// TODO make speed configurable
		float speed = 0.2f;

		// ok now calc direction ( x )
		float xVel = 0;
		if (getX() == finalBx) {
			if (xOffset > 0) {
				xVel = -speed;
			}
		} else {
			if (finalBx > getX()) {
				xVel = -speed;
			} else {
				xVel = speed;
			}
		}

		// ok now calc direction ( y )
		float yVel = 0;
		if (getY() == finalBy) {
			if (yOffset > 0) {
				yVel = -speed;
			}
		} else {
			if (finalBy > getY()) {
				yVel = -speed;
			} else {
				yVel = speed;
			}
		}

		xOffset += xVel;
		yOffset += yVel;

		if (xOffset > blocksize) {
			setX(getX() - 1);
			xOffset = 0;
		}
		if (yOffset > blocksize) {
			setY(getY() - 1);
			yOffset = 0;
		}
		if (xOffset < 0) {
			setX(getX() + 1);
			xOffset = blocksize;
		}
		if (yOffset < 0) {
			setY(getY() + 1);
			yOffset = blocksize;
		}
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
		// add old position to history
		xPos.add(cx);

		// update position in blockcollector
		BlockCollector.getInstance().moveBlock(cx, cy, newX, cy);

		cx = newX;
	}

	public void setY(int newY) {
		// add current position to hostory
		yPos.add(cy);

		// update position in blockcollector
		BlockCollector.getInstance().moveBlock(cx, cy, cx, newY);

		cy = newY;
	}

	public int getX() {
		return cx;
	}

	public int getY() {
		return cy;
	}

	public float getxOffset() {
		return xOffset;
	}

	public void setxOffset(float xOffset) {
		this.xOffset = xOffset;
	}

	public float getyOffset() {
		return yOffset;
	}

	public void setyOffset(float yOffset) {
		this.yOffset = yOffset;
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

	/**
	 * Use one of the following methods instead triggerPlayerTouch<br>
	 * triggerNpcTouch<br>
	 * triggerPlayerTouch<br>
	 * 
	 * Using this method triggerPlayerTouch is executed !
	 */
	@Deprecated
	public void triggerTouch() {
		triggerPlayerTouch();
	}

	public void triggerPlayerTouch() {
		for ( Trigger trigger : this.touchListener ) {
			if (trigger.playerCanTrigger()) {
				trigger.trigger();
			}
		}
	}

	public void triggerNpcTouch() {
		for ( Trigger trigger : this.touchListener ) {
			if (trigger.npcCanTrigger()) {
				trigger.trigger();
			}
		}
	}

	public boolean hasCollision() {
		return true;
	}

	public void setRoute(Route cRoute) {
		route = cRoute;
		routeIndex = 0;
	}

	public void startPatroll() {
		patroll = true;
	}
}
