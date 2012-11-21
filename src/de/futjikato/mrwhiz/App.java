package de.futjikato.mrwhiz;

import de.futjikato.mrwhiz.xml.Map;

public class App {

	private boolean debug = false;
	private static App instance;

	private GameStates state = GameStates.WORLDMAP;
	private GameStates nextState;

	private Map nextGameMap;

	/**
	 * Main
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		// create new app instance
		App app = new App();
		App.instance = app;

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

	private App() {

	}

	private void enableDebug() {
		this.debug = true;
	}

	public void setNextStep(GameStates gs) {
		this.state = gs;
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
}
