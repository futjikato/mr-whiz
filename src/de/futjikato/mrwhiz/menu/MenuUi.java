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
import de.matthiasmann.twl.Button;
import de.matthiasmann.twl.ComboBox;
import de.matthiasmann.twl.GUI;
import de.matthiasmann.twl.Widget;
import de.matthiasmann.twl.model.SimpleChangableListModel;
import de.matthiasmann.twl.renderer.lwjgl.LWJGLRenderer;
import de.matthiasmann.twl.theme.ThemeManager;

public class MenuUi extends Widget {

	private GUI gui;
	private ThemeManager theme;

	private Button playButton;

	private boolean isStopRequested = false;
	private ComboBox<ResolutionListEntry> resolutionSelect;

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
		this.playButton = new Button();
		this.playButton.setText("Play");
		this.playButton.addCallback(new Runnable() {

			@Override
			public void run() {
				App.getInstance().setNextStep(GameStates.WORLDMAP);
				MenuUi.this.isStopRequested = true;
			}
		});
		this.add(this.playButton);

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
		this.add(this.resolutionSelect);
	}

	@Override
	protected void layout() {
		this.playButton.setPosition(20, 20);
		this.playButton.adjustSize();

		this.resolutionSelect.setPosition(20, 300);
		this.resolutionSelect.adjustSize();

	}

	public boolean isStopRequested() {
		return this.isStopRequested;
	}
}
