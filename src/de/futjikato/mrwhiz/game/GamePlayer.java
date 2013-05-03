package de.futjikato.mrwhiz.game;

import de.futjikato.mrwhiz.game.ui.InputHandler;
import de.futjikato.mrwhiz.physic.ObjectState;
import de.futjikato.mrwhiz.physic.PhysicalObject;
import de.futjikato.mrwhiz.rendering.positioning.Coordinate;
import de.futjikato.mrwhiz.rendering.positioning.FloatCalculator;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import java.io.File;
import java.io.FileNotFoundException;

public class GamePlayer implements PhysicalObject {

    protected static final float WALKING_SPEED = 0.14f;

    protected static final float RUNNING_SPEED = 0.24f;

    protected Coordinate<Float> position;

    protected ObjectState physicalState;

    protected InputHandler inputHandler;

    private boolean running;

    public GamePlayer() {
        physicalState = new ObjectState(this);

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

    public void setRunning(boolean running) {
        this.running = running;
    }

    public void moveLeft() {
        if(running) {
            physicalState.setXv(-RUNNING_SPEED);
        } else {
            physicalState.setXv(-WALKING_SPEED);
        }
    }

    public void moveRight() {
        if(running) {
            physicalState.setXv(RUNNING_SPEED);
        } else {
            physicalState.setXv(WALKING_SPEED);
        }
    }

    public boolean canJump() {
        return !physicalState.isFalling();
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

    @Override
    public boolean isBlocked() {
        return true;
    }
}