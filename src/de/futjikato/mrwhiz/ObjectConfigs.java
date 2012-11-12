package de.futjikato.mrwhiz;

public class ObjectConfigs {
	private int x;
	private int y;
	private int width;
	private int height;

	private static final int DEFAULT_X = 0;
	private static final int DEFAULT_Y = 0;
	private static final int DEFAULT_WIDTH = 50;
	
	public ObjectConfigs(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public ObjectConfigs(int x, int y, int width) {
		// per default make quadratic objects
		this(x, y, width, width);
	}
	
	public ObjectConfigs(int x, int y) {
		this(x, y, ObjectConfigs.DEFAULT_WIDTH);
	}
	
	public ObjectConfigs(int x) {
		this(x, ObjectConfigs.DEFAULT_Y);
	}
	
	public ObjectConfigs() {
		this(ObjectConfigs.DEFAULT_X);
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
}
