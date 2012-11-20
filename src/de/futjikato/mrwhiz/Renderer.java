package de.futjikato.mrwhiz;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Input;

public abstract class Renderer {
	
	protected int width = 600;
	protected int height = 600;
	protected String title = "<Missing title>";
	protected boolean isStoped;
	
	protected Input input;
	
	public void start() {
		
		// stoped to false
		this.isStoped = false;
		
		try {
			this.renderLoop();
		} catch (Exception e) {
			this.isStoped = true;
			
			e.printStackTrace();
		}
	}
	
	public void stop() {
		this.isStoped = true;
	}
	
	private void renderLoop() throws Exception {
		this.init();
		this.initFPS();
		
		while(!this.isStoped) {
			// clear
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
			GL11.glLoadIdentity();
			
			// render
			GL11.glMatrixMode(GL11.GL_PROJECTION);
		    GL11.glLoadIdentity();
		    GL11.glOrtho(0, Display.getDisplayMode().getWidth(), Display.getDisplayMode().getHeight(), 0, -1, 1);
		    GL11.glMatrixMode(GL11.GL_MODELVIEW);
		    
		    // enable textures
		    GL11.glEnable(GL11.GL_TEXTURE_2D);
		    // enable alpha blending
        	GL11.glEnable(GL11.GL_BLEND);
        	GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			
		    long delta = this.getDelta();
		    
		    this.handleInput(delta, this.input);
		    this.renderScene(delta);
		    this.renderUi(delta);
		    
		    // update screen
			Display.update();
			
			this.updateFPS();
		}
	}
	
	protected abstract void renderScene(long delta);
	protected abstract void renderUi(long delta);
	protected abstract void handleInput(long delta, Input input);
	
	protected void init() throws LWJGLException {
		Display.setDisplayMode(new DisplayMode(this.width, this.height));
		Display.setTitle(this.title);
		Display.create();
		
		this.input = new Input(Display.getHeight());
	}
	
	/**
	 * ++++++++++++++++++++++++++++++++++++++++++++
	 * FPS calculation and stuff
	 * ++++++++++++++++++++++++++++++++++++++++++++
	 */
	
	protected long lastFrame;
	protected long lastFPS;
	protected long fps;
	
	/**
	 * Get the accurate system time
	 * 
	 * @return The system time in milliseconds
	 */
	public long getTime() {
	    return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}
	
	/** 
	 * Calculate how many milliseconds have passed 
	 * since last frame.
	 * 
	 * @return milliseconds passed since last frame 
	 */
	public int getDelta() {
	    long time = getTime();
	    int delta = (int) (time - this.lastFrame);
	    this.lastFrame = this.getTime();
	    
	    return delta;
	}
	
	protected abstract void printFps(long fps);
	
	/**
	 * Calculate the FPS and set it in the title bar
	 */
	private void updateFPS() {
	    if (getTime() - this.lastFPS > 1000) {
	    	this.printFps(this.fps);
	        this.fps = 0; //reset the FPS counter
	        this.lastFPS += 1000; //add one second
	    }
	    fps++;
	}
	
	private void initFPS() {
		// init fps variables
		this.lastFrame = this.getTime();
		this.lastFPS = this.getTime();
		this.fps = 0;
	}
}
