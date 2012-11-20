package de.futjikato.mrwhiz.map;

import java.io.IOException;
import java.net.URL;

import org.lwjgl.LWJGLException;

import de.futjikato.mrwhiz.App;
import de.matthiasmann.twl.GUI;
import de.matthiasmann.twl.Label;
import de.matthiasmann.twl.Widget;
import de.matthiasmann.twl.renderer.lwjgl.LWJGLRenderer;
import de.matthiasmann.twl.theme.ThemeManager;

public class MapUi extends Widget {
	
	private GUI gui;
	private ThemeManager theme;
	
	// ui elements
	private Label fps;
	private long fpsValue;

	public MapUi() throws LWJGLException {
		this.initUi();
		
		LWJGLRenderer renderer = new LWJGLRenderer();
		this.gui = new GUI(this, renderer);
		
		try {
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
	}
	
	@Override
	protected void layout() {
		// fps label
		this.fps.setPosition(20, 20);
		this.fps.adjustSize();
	}
	
	public void update() {
		// update fps label
		this.fps.setText(String.format("FPS: %d", this.fpsValue));
		
		this.gui.update();
	}

	public void setFps(long fps) {
		this.fpsValue = fps;
	}
}
