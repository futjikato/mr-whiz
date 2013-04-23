package de.futjikato.mrwhiz.rendering;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import java.util.HashMap;

/**
 *
 */
public class Structure {

    protected Image texture;

    public Structure(HashMap<String, String> definition) {
        try {
            texture = new Image(definition.get("texture"));
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    public Image getTexture() {
        return texture;
    }

    public boolean doRender() {
        // @todo read from definition with fallback to true
        return true;
    }
}
