package de.futjikato.mrwhiz.game;

import de.futjikato.mrwhiz.game.ui.InputHandler;
import de.futjikato.mrwhiz.physic.ObjectState;
import de.futjikato.mrwhiz.rendering.positioning.Coordinate;
import de.futjikato.mrwhiz.rendering.positioning.FloatCalculator;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import java.io.File;
import java.io.FileNotFoundException;

public class GamePlayer {

    protected Coordinate<Float> position;

    protected ObjectState physicalState;

    protected InputHandler inputHandler;

    protected float walkingSpeed = 0.025f;
    private boolean running;

    public GamePlayer() {
        physicalState = new ObjectState();

        try {
            File keymap = new File("resources/data/keymap.json");
            inputHandler = InputHandler.create(keymap, this);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void setPosition(float x, float y) {
        position = new Coordinate<Float>(x, y, FloatCalculator.INSTANCE);
    }

    public Image getTexture() throws SlickException {
        return new Image("resources/images/backgrounds/dev.png");
    }

    public Coordinate<Float> getPosition() {
        return position;
    }

    public ObjectState getPhysicalState() {
        return physicalState;
    }

    public float getWalkingSpeed() {
        return walkingSpeed;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public void moveLeft() {
        physicalState.setXv(-0.2f);
    }

    public void moveRight() {
        physicalState.setXv(0.2f);
    }

    public boolean canJump() {
        return true;
    }

    public void jump() {
        physicalState.setYv(-0.2f);
    }

    public void intersect(long delta, Input input) {
        if(inputHandler != null) {
            inputHandler.intersect(input);
        }

        float xv = physicalState.getXv() * delta;
        float yv = physicalState.getYv() * delta;
        physicalState.decrease();

        float x = position.getX() + xv;
        float y = position.getY() + yv;

        position = new Coordinate<Float>(x, y, FloatCalculator.INSTANCE);
    }
}