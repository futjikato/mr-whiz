package de.futjikato.mrwhiz.menu;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import de.futjikato.mrwhiz.App;
import de.futjikato.mrwhiz.GameStates;
import de.futjikato.mrwhiz.Util;
import de.futjikato.mrwhiz.ui.ResolutionListEntry;
import de.matthiasmann.twl.BoxLayout;
import de.matthiasmann.twl.Button;
import de.matthiasmann.twl.ComboBox;
import de.matthiasmann.twl.GUI;
import de.matthiasmann.twl.ToggleButton;
import de.matthiasmann.twl.Widget;
import de.matthiasmann.twl.model.SimpleChangableListModel;
import de.matthiasmann.twl.renderer.lwjgl.LWJGLRenderer;
import de.matthiasmann.twl.theme.ThemeManager;

public class MenuUi extends Widget {

	private GUI gui;
	private ThemeManager theme;

	private BoxLayout topBox;
	private BoxLayout bottomBox;

	private Button playButton;
	private ComboBox<ResolutionListEntry> resolutionSelect;
	private ToggleButton fullscreenCheckbox;

	private boolean isStopRequested = false;

	public MenuUi() throws LWJGLException {

		this.initUi();

		// init gui
		LWJGLRenderer renderer = new LWJGLRenderer();
		this.gui = new GUI(this, renderer);

		try {
			// load theme
			String themePath = "themes/whiz.xml";
			URL res = App.class.getClassLoader().getResource(themePath);
			this.theme = ThemeManager.createThemeManager(res, renderer);
			this.gui.applyTheme(this.theme);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			System.out.println("Due to an error the UI cannot be displayed.");
			e.printStackTrace();
		}
	}

	public void update() {
		this.gui.update();
	}

	private void initUi() {

		topBox = new BoxLayout(BoxLayout.Direction.HORIZONTAL);
		topBox.setTheme("menubox");
		bottomBox = new BoxLayout(BoxLayout.Direction.HORIZONTAL);
		bottomBox.setTheme("menubox");

		this.playButton = new Button();
		this.playButton.setText("Play");
		this.playButton.addCallback(new Runnable() {

			@Override
			public void run() {
				App.getInstance().setNextStep(GameStates.WORLDMAP);
				MenuUi.this.isStopRequested = true;
			}
		});
		bottomBox.add(this.playButton);

		List<ResolutionListEntry> resEntryList = Util.getSystemResolutionList();
		SimpleChangableListModel<ResolutionListEntry> resolutionSelectListModel = new SimpleChangableListModel<ResolutionListEntry>(resEntryList);
		this.resolutionSelect = new ComboBox<ResolutionListEntry>(resolutionSelectListModel);
		this.resolutionSelect.setComputeWidthFromModel(true);
		this.resolutionSelect.setSelected(0);
		this.resolutionSelect.addCallback(new Runnable() {

			@Override
			public void run() {
				int selected = MenuUi.this.resolutionSelect.getSelected();
				List<ResolutionListEntry> resEntryList = Util.getSystemResolutionList();
				ResolutionListEntry selectedEntry = resEntryList.get(selected);

				try {
					Display.setDisplayMode(new DisplayMode(selectedEntry.getWidth(), selectedEntry.getHeight()));
				} catch (LWJGLException e) {
					e.printStackTrace();
				}
			}
		});
		topBox.add(this.resolutionSelect);

		this.fullscreenCheckbox = new ToggleButton();
		this.fullscreenCheckbox.setText("Fullscreen");
		this.fullscreenCheckbox.setTheme("checkbox");
		this.fullscreenCheckbox.addCallback(new Runnable() {

			@Override
			public void run() {
				App.getInstance().toggleFullscreen();
			}
		});
		topBox.add(this.fullscreenCheckbox);

		this.add(bottomBox);
		this.add(topBox);
	}

	@Override
	protected void layout() {
		bottomBox.adjustSize();
		bottomBox.setPosition(0, Display.getHeight() - bottomBox.getHeight());
		bottomBox.setInnerSize(Display.getWidth(), bottomBox.getHeight());

		topBox.adjustSize();
		topBox.setPosition(0, 0);
	}

	public boolean isStopRequested() {
		return this.isStopRequested || Display.isCloseRequested();
	}
}
