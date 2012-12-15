package de.futjikato.mrwhiz.ui;

import org.lwjgl.opengl.DisplayMode;

public class ResolutionListEntry implements Comparable<ResolutionListEntry> {

	public ResolutionListEntry(int height, int width, int frequency) {
		this.setFreq(frequency);
		this.setHeight(height);
		this.setWidth(width);
	}

	public ResolutionListEntry(DisplayMode mode) {
		this(mode.getHeight(), mode.getWidth(), mode.getFrequency());
	}

	private int height;
	private int width;
	private int freq;

	public int getHeight() {
		return height;
	}

	protected void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	protected void setWidth(int width) {
		this.width = width;
	}

	public int getFreq() {
		return freq;
	}

	protected void setFreq(int freq) {
		this.freq = freq;
	}

	@Override
	public String toString() {
		return String.format("%d * %d ( %d Hrtz )", this.getWidth(), this.getHeight(), this.getFreq());
	}

	/**
	 * Compares this resolution list entry with an other one.<br>
	 * This method is used for the listing order<br>
	 * Sort order is pixel decity.
	 */
	@Override
	public int compareTo(ResolutionListEntry o) {
		int myDencity = this.getHeight() * this.getWidth();
		int oDencity = o.getHeight() * o.getWidth();

		int diff = myDencity - oDencity;
		return diff;
	}
}
