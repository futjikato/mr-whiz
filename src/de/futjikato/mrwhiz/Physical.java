package de.futjikato.mrwhiz;

public abstract class Physical {

	public static final int COLLISION_X_NONE = 0;
	public static final int COLLISION_X_BLOCKED = 1;
	public static final int COLLISION_Y_NONE = 3;
	public static final int COLLISION_Y_BLOCKED = 5;

	private float x;
	private float y;

	private float yvel = 0;
	private float xvel = 0;

	private float maxYVal;
	private float grip;

	public float getYVel() {
		return this.yvel;
	}

	public void setYVel(float vel) {
		this.yvel = vel;
	}

	public float getXvel() {
		return xvel;
	}

	public void setXvel(float xvel) {
		this.xvel = xvel;
	}

	public float getMaxYVal() {
		return maxYVal;
	}

	public void setMaxYVal(float maxYVal) {
		this.maxYVal = maxYVal;
	}

	public float getGrip() {
		return grip;
	}

	public void setGrip(float grip) {
		this.grip = grip;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	protected void calcNewPos(int blocksize, long delta) {

		float x = getX();
		float y = getY();

		// calc new velocitys
		float yv = this.getYVel();
		float xv = this.getXvel();

		// calculate new falling speed
		if (yv < 0) {
			yv += delta * 0.005f;
		} else {
			yv += delta * 0.002f;
		}

		// calculate new x-axis speed
		if (xv > 0) {
			xv -= delta * 0.01f;
			if (xv < 0)
				xv = 0;
		} else if (xv < 0) {
			xv += delta * 0.01f;
			if (xv > 0)
				xv = 0;
		}

		// limit fall speed
		if (yv > this.getMaxYVal()) {
			yv = this.getMaxYVal();
		}

		// set new velocitys
		this.setYVel(yv);
		this.setXvel(xv);

		// set new y position
		float ny = delta * yv;
		float nx = delta * xv;

		int yCollisionType = this.yCol(x, y + ny, blocksize);
		if (yCollisionType == COLLISION_Y_NONE) {
			this.setY(this.getY() + ny);
		} else {
			// reset y velocity on landing somewhere
			this.setYVel(0);
			handleCollision(yCollisionType);
		}

		int xCollisionType = this.xCol(x + nx, y, blocksize);
		if (xCollisionType == COLLISION_X_NONE) {
			this.setX(this.getX() + nx);
		} else {
			handleCollision(xCollisionType);
		}
	}

	protected abstract int yCol(float x, float y, int blocksize);

	protected abstract int xCol(float x, float y, int blocksize);

	protected abstract void handleCollision(int type);
}
