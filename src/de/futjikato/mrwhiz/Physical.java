package de.futjikato.mrwhiz;

public abstract class Physical {

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

	protected float[] calcNewPos(float x, float y, int blocksize, long delta) {
		// calc new velocitys
		float yv = this.getYVel();
		float xv = this.getXvel();

		yv += (yv < 0) ? (delta * 0.015f) : (delta * 0.0015f);
		if (xv > 0) {
			xv -= delta * 0.1f;
			if (xv < 0)
				xv = 0;
		} else if (xv < 0) {
			xv = delta * 0.1f;
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

		if (this.yCol(x, y + ny, blocksize)) {
			y += ny;
		}

		if (this.xCol(x + nx, y, blocksize)) {
			x += nx;
		}

		return new float[] { x, y };
	}

	protected abstract boolean yCol(float x, float y, int blocksize);

	protected abstract boolean xCol(float x, float y, int blocksize);
}
