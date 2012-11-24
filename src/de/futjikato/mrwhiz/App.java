package de.futjikato.mrwhiz;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.Input;

import de.futjikato.mrwhiz.xml.Map;

public class App {

	private boolean debug = false;
	private static App instance;

	private GameStates state = GameStates.WORLDMAP;
	private GameStates nextState;

	private Map nextGameMap;
	private Input input;

	/**
	 * Main
	 * 
	 * @param args
	 * @throws LWJGLException
	 */
	public static void main(String[] args) throws LWJGLException {

		// create new app instance
		App app = new App();

		// parse arguments
		for ( String arg : args ) {
			if (arg.equals("-debug")) {
				app.enableDebug();
			}
		}

		app.loop();
	}

	/**
	 * Return the app instance
	 * 
	 * @return App instance
	 */
	public static App getInstance() {
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

	private App() throws LWJGLException {
		App.instance = this;
		this.createWindow();
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

	public void setNextStep(GameStates gs) {
		this.nextState = gs;
	}

	private void loop() {
		while (this.state != null && this.state != GameStates.QUIT) {
			// set next step to exit state of new state
			this.nextState = this.state.getExistState();

			// start state now
			this.state.start();

			this.state = this.nextState;
		}
	}

	public void setNextGameMap(Map map) {
		this.nextGameMap = map;
	}

	public Map getNextGameMap() {
		return this.nextGameMap;
	}

	public Input getInput() {
		return this.input;
	}
}
