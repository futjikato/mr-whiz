package de.futjikato.mrwhiz.rendering;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import java.util.HashMap;

/**
 *
 */
public class Structure {

    protected Image texture;

    protected int render = 0;

    //@todo make this an internal enum
    protected String textureMode = "fit";

    public Structure(HashMap<String, String> definition) {
        try {
            texture = new Image(definition.get("texture"));
        } catch (SlickException e) {
            e.printStackTrace();
        }

        if(definition.containsKey("render")) {
            render = Integer.valueOf(definition.get("render"));
        }

        if(definition.containsKey("textureMode")) {
            textureMode = definition.get("textureMode");
        }
    }

    public Image getTexture() {
        return texture;
    }

    public boolean doRender() {
        return render == 1;
    }

    public String getTextureMode() {
        return textureMode;
    }
}
