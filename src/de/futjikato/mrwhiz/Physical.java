package de.futjikato.mrwhiz;

import java.util.Observable;

public abstract class Physical extends Observable {
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
			yv += delta * 0.003f;
		}

		// calculate new x-axis speed
		if (xv > 0) {
			xv -= delta * 0.15f;
			if (xv < 0)
				xv = 0;
		} else if (xv < 0) {
			xv += delta * 0.15f;
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

		if (this.checkCollision(x, y + yv, 0)) {
			this.setY(this.getY() + yv);
		} else {
			if (yv > 0) {
				// reset y velocity on landing somewhere
				this.setYVel(0);
			} else {
				// add initial down movement on hit at top to prevent physic bug
				this.setYVel(0.002f);
			}
		}

		if (this.checkCollision(x + xv, y, 1)) {
			this.setX(this.getX() + xv);
		}
	}

	protected abstract boolean checkCollision(float x, float y, int type);
}
