package de.futjikato.mrwhiz.ui;

import de.matthiasmann.twl.GUI;
import de.matthiasmann.twl.ProgressBar;
import de.matthiasmann.twl.renderer.Image;

public final class VerticalProgressBar extends ProgressBar {

	private org.newdawn.slick.Image slickimage;

	public VerticalProgressBar() {
		super();
	}

	public void setSlickProgressImage(org.newdawn.slick.Image slickimage) {
		this.slickimage = slickimage;
	}

	protected org.newdawn.slick.Image getSlickProgressImage() {
		return this.slickimage;
	}

	@Override
	protected void paintWidget(GUI gui) {
		int height = getInnerHeight();
		float value = this.getValue();

		// first try to get a nice slick image
		org.newdawn.slick.Image slickprogress = this.getSlickProgressImage();
		if (slickprogress == null) {
			// if no slick image is provided use twl image
			Image progressImage = this.getProgressImage();
			int imageWidth = progressImage.getWidth();

			if (progressImage != null) {
				progressImage.draw(getAnimationState(), getInnerX(), getInnerY() + (int) (height * (1.0f - value)), imageWidth, (int) (height * value));
			}
		} else {
			int imageWidth = slickprogress.getWidth();
			slickprogress.draw(getInnerX(), getInnerY() + (int) (height * value), getInnerX() + imageWidth, getInnerY() + (int) (height * (1.0f - value)), 0, height, imageWidth, (int) (imageWidth * value));
		}
	}
}
