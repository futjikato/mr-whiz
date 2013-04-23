package de.futjikato.mrwhiz;

import com.google.gson.Gson;
import de.futjikato.mrwhiz.game.BlockDefinitions;
import de.futjikato.mrwhiz.game.GamePlayer;
import de.futjikato.mrwhiz.game.GameRenderer;
import de.futjikato.mrwhiz.game.Map;
import de.futjikato.mrwhiz.rendering.ImageStorage;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.Input;

import java.io.File;
import java.io.IOException;

public class App {

	static final long MAIN_VERSION = 1;
    private static String mapName;

    private boolean debug = false;
	private boolean isUnitTest = false;
	private boolean fullscreen = false;
	private static App instance;

	private Map nextGameMap;
	private Input input;

	/**
	 * Main
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		// create new app instance
		App app = getInstance();

		app.defineLwjglLibraryPath();

		// parse arguments
		for(int i = 0 ; i < args.length ; ) {
            // get Launchoption
            LaunchOptions option = LaunchOptions.getByOption(args[i++]);
            int paramCount = option.getParamCount();
            // build argument parameter array
            String[] optionParameter = new String[paramCount];
            for(int j = 0 ; j < paramCount ; j++) {
                optionParameter[j] = args[i++];
            }
            // process option
            option.process(optionParameter);
        }

		app.createWindow();
		app.loadMap();

		// remove display on app exit
		Display.destroy();
	}

    private void loadMap() {
        File structureFile = new File("resources/data/level/" + mapName + ".png");
        File defineFile = new File("resources/data/level/" + mapName + ".defines");

        // @todo show loading screen

        // load structures
        ImageStorage structure = null;
        BlockDefinitions definition = null;
        try {
            definition = BlockDefinitions.create(defineFile);
            structure = new ImageStorage(structureFile, definition);

            Map map = new Map(structure, definition);
            GameRenderer.getInstance().startMap(map);
        } catch (Exception e) {
            e.printStackTrace();
            //@todo show error window
        }
    }

    public static void setMapName(String mapName) {
        App.mapName = mapName;
    }

    public void defineLwjglLibraryPath() throws Exception {
		String os = System.getProperty("os.name");
		if (os.toLowerCase().contains("windows")) {
			System.setProperty("org.lwjgl.librarypath", System.getProperty("user.dir") + "\\libs\\lwjgl-2.9.0\\native\\windows");
		} else if (os.toLowerCase().contains("mac")) {
			System.setProperty("org.lwjgl.librarypath", System.getProperty("user.dir") + "/libs/lwjgl-2.9.0/native/macosx");
		} else {
			throw new Exception("For this os we haven´t jet assigned the native libs : \"" + os + "\"");
		}
	}

	/**
	 * Return the app instance
	 * 
	 * @return App instance
	 */
	public static App getInstance() {
		if (App.instance == null) {
			App.instance = new App();
		}
		return App.instance;
	}

	/**
	 * Return true if the app is started with debug flag
	 * 
	 * @return debug flag
	 */
	public boolean isDebug() {
		return this.debug;
	}

	public boolean isUnittest() {
		return this.isUnitTest;
	}

	public void enableUnittestMode() throws Exception {
		this.defineLwjglLibraryPath();
		this.isUnitTest = true;
	}

	protected void createWindow() throws LWJGLException {
		Display.setDisplayMode(new DisplayMode(500, 500));
		Display.setTitle("Mr-Whiz - dev version");
		Display.create();

		this.input = new Input(Display.getHeight());
	}

	private void enableDebug() {
		this.debug = true;
	}

	public Input getInput() {
		return this.input;
	}

	public void toggleFullscreen() {
		try {
			this.fullscreen = !this.fullscreen;
			Display.setFullscreen(this.fullscreen);
			System.out.println("fullscreen : " + this.fullscreen);
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
	}
}
