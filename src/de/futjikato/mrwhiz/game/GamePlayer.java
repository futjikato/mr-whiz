package de.futjikato.mrwhiz.game;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class GamePlayer {

    protected float x;

    protected float y;

    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Image getTexture() throws SlickException {
        return new Image("resources/images/backgrounds/dev.png");
    }

}