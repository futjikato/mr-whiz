package de.futjikato.mrwhiz.xml.attributes;

import java.util.ArrayList;
import java.util.List;

import de.futjikato.mrwhiz.xml.XmlObject;

public class Dimensions extends XmlAttribute {

	private List<Integer> xPositions = new ArrayList<Integer>();

	private List<Integer> yPositions = new ArrayList<Integer>();

	private List<Integer> widths = new ArrayList<Integer>();

	private List<Integer> heights = new ArrayList<Integer>();

	@Override
	public void handleValue(String value, XmlObject xmlObject) {
		// value can be "10,10,10,10"
		String[] parts = value.split(",");

		if (parts.length == 4) {
			this.heights.add(Integer.parseInt(parts[3]));
		}

		if (parts.length >= 3) {
			this.widths.add(Integer.parseInt(parts[2]));
		}

		if (parts.length >= 2) {
			this.yPositions.add(Integer.parseInt(parts[1]));
		}

		if (parts.length >= 1) {
			this.xPositions.add(Integer.parseInt(parts[0]));
		}
	}

	public int getX() {
		return xPositions.get(xPositions.size() - 1);
	}

	public int getY() {
		return yPositions.get(yPositions.size() - 1);
	}

	public int getW() {
		if (widths.size() == 0) {
			return 0;
		}
		return widths.get(widths.size() - 1);
	}

	public int getH() {
		if (heights.size() == 0) {
			return 0;
		}
		return heights.get(heights.size() - 1);
	}

	public void moveByDimension(Dimensions targetMove) {
		int newX = this.getX() + targetMove.getX();
		int newY = this.getY() + targetMove.getY();
		int newW = this.getW() + targetMove.getW();
		int newH = this.getH() + targetMove.getH();

		this.xPositions.add(newX);
		this.yPositions.add(newY);
		this.widths.add(newW);
		this.heights.add(newH);
	}

	public void rollback(int steps) throws ArrayIndexOutOfBoundsException {
		if (this.xPositions.size() < steps || this.yPositions.size() < steps || this.widths.size() < steps || this.heights.size() < steps) {
			throw new ArrayIndexOutOfBoundsException();
		}

		this.xPositions = this.xPositions.subList(0, this.xPositions.size() - steps);
		this.yPositions = this.yPositions.subList(0, this.yPositions.size() - steps);
		this.widths = this.widths.subList(0, this.widths.size() - steps);
		this.heights = this.heights.subList(0, this.heights.size() - steps);
	}

	public void rollback() throws ArrayIndexOutOfBoundsException {
		this.rollback(this.xPositions.size() - 1);
	}

	@Override
	public void copyFrom(XmlAttribute o) throws AttributeCopyError {
		if (!(o instanceof Dimensions)) {
			throw new AttributeCopyError();
		}

		Dimensions oDim = (Dimensions) o;

		// copy all values
		this.heights.add(oDim.getH());
		this.widths.add(oDim.getW());
		this.xPositions.add(oDim.getX());
		this.yPositions.add(oDim.getY());
	}

	public String toString() {
		return String.format("xywh( %d, %d, %d, %d )", this.getX(), this.getY(), this.getW(), this.getH());
	}
}
