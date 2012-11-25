package de.futjikato.mrwhiz;

public abstract class Physical {

	private float yvel;

	public static final float MAX_Y_SPPED = 1.8f;

	protected float getYVel() {
		return this.yvel;
	}

	protected void setYVel(float vel) {
		this.yvel = vel;
	}

	protected float calcY(float y, long delta) {
		// calc new falling speed
		float yv = this.getYVel();
		yv += (yv < 0) ? (delta * 0.015f) : (delta * 0.0015f);

		// limit fall speed
		if (yv > MAX_Y_SPPED) {
			yv = MAX_Y_SPPED;
		}

		// set new falling speed
		this.setYVel(yv);

		// set new y position
		float ny = delta * yv;

		if (this.checkCollide(y + ny)) {
			y += ny;
		}

		return y;
	}

	protected abstract boolean checkCollide(float y);
}
