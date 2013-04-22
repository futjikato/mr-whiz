package de.futjikato.mrwhiz.rendering;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import java.util.HashMap;

/**
 *
 */
public class Structure {

    protected Image texture;

    protected int x;

    protected int y;

    public Structure(HashMap<String, String> definition, int x, int y) {

        this.x = x;
        this.y = y;

        try {
            texture = new Image(definition.get("texture"));
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    public void render(Coordinate relativeTo) {
        float realX = x - relativeTo.getX();
        float realY = y - relativeTo.getY();

        texture.draw(realX * 60, realY * 60, texture.getWidth(), texture.getHeight());
    }
}
