package de.futjikato.mrwhiz.game;

import java.io.IOException;
import java.net.URL;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;

import de.futjikato.mrwhiz.App;
import de.matthiasmann.twl.GUI;
import de.matthiasmann.twl.Label;
import de.matthiasmann.twl.Widget;
import de.matthiasmann.twl.renderer.lwjgl.LWJGLRenderer;
import de.matthiasmann.twl.theme.ThemeManager;

public final class GameUi extends Widget {

	private GamePlayer player;
	private GUI gui;
	private ThemeManager theme;

	private Label fps;
	private long fpsValue;

	private Label score;

	public GameUi(GamePlayer player) throws LWJGLException {
		this.player = player;

		// init ui
		this.initUi();

		// init gui
		LWJGLRenderer renderer = new LWJGLRenderer();
		this.gui = new GUI(this, renderer);

		try {
			// load theme
			URL res = App.class.getClassLoader().getResource("themes/whiz.xml");
			this.theme = ThemeManager.createThemeManager(res, renderer);
			this.gui.applyTheme(this.theme);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void initUi() {
		// init fps label
		this.fps = new Label();
		this.fps.setText(String.format("FPS: %d", this.fpsValue));
		this.add(this.fps);

		// init score label
		this.score = new Label();
		this.score.setText(String.format("Score: %d", this.player.getScore()));
		this.add(this.score);
	}

	public void update() {
		// update fps label
		this.fps.setText(String.format("FPS: %d", this.fpsValue));

		// update score
		this.score.setText(String.format("Score: %d", this.player.getScore()));

		this.gui.update();
	}

	@Override
	protected void layout() {
		// fps label
		this.fps.setPosition(20, Display.getHeight() - 60);
		this.fps.adjustSize();

		// score label
		this.score.setPosition(20, Display.getHeight() - 90);
		this.score.adjustSize();
	}

	public void setFps(long fps) {
		this.fpsValue = fps;
	}
}
