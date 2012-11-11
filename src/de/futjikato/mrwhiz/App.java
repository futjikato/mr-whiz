package de.futjikato.mrwhiz;

public class App {

	private boolean debug = false;
	private static App instance;
	private GameStates state = GameStates.WORLDMAP;
	
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
		for(String arg : args) {
			if(arg == "-debug") {
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
	
	private void loop() {
		while(this.state != null && this.state != GameStates.QUIT) {
			// get runner
			this.state.start();
			
			// on exit get next state
			this.state = this.state.getExistState();
		}
	}
}
