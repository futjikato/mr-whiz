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
public class ObjectState<T extends PhysicalObject> {

    protected T baseObject;

    protected boolean isFalling = false;

    protected float xv;

    protected float yv;

    public ObjectState(T object) {
        baseObject = object;
    }

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
            setFalling(true);
            if(baseObject.isBlocked()) {
                yv = 0;
                setFalling(false);
            }
        } else {
            yv += 0.001f;
            setFalling(true);
        }
    }
}
