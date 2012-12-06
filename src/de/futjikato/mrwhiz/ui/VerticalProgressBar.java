package de.futjikato.mrwhiz.ui;

import de.matthiasmann.twl.GUI;
import de.matthiasmann.twl.ProgressBar;
import de.matthiasmann.twl.renderer.Image;

public final class VerticalProgressBar extends ProgressBar {

	public VerticalProgressBar() {
		super();
	}

	@Override
	protected void paintWidget(GUI gui) {
		int height = getInnerHeight();
		float value = this.getValue();
		Image progressImage = this.getProgressImage();
		int imageWidth = progressImage.getWidth();

		if (progressImage != null) {
			progressImage.draw(getAnimationState(), getInnerX(), getInnerY(), imageWidth, (int) (height * value));
		}
	}
}
