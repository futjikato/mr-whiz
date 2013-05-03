package de.futjikato.mrwhiz.physic;

import de.futjikato.mrwhiz.rendering.positioning.Coordinate;
import de.futjikato.mrwhiz.rendering.positioning.FloatCalculator;

/**
 * Created with IntelliJ IDEA.
 * User: Moritz
 * Date: 27.04.13
 * Time: 18:38
 * To change this template use File | Settings | File Templates.
 */
public class ObjectState {

    protected boolean isFalling = false;

    protected float xv;

    protected float yv;

    public boolean isFalling() {
        return isFalling;
    }

    public void setFalling(boolean falling) {
        isFalling = falling;
    }

    public float getXv() {
        return xv;
    }

    public void setXv(float xv) {
        this.xv = xv;
    }

    public float getYv() {
        return yv;
    }

    public void setYv(float yv) {
        this.yv = yv;
    }

    public void decrease() {
        // decrease X speed
        if(xv < 0.01f && xv > -0.01f) {
            xv = 0.0f;
        } else {
            if(xv > 0) {
                xv -= 0.01f;
            } else {
                xv += 0.01f;
            }
        }

        // decrease Y speed
        if(yv >= 0) {
            yv += 0.0001f;
            if(true) { // isBlocked
                yv = 0;
            }
        } else {
            yv += 0.001f;
        }
    }
}
