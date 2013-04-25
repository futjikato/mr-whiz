package de.futjikato.mrwhiz.rendering;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import java.util.HashMap;

/**
 *
 */
public class Structure {

    public static final String OPT_RENDER = "1";

    public static final String OPT_TEXTUREMODE_FIT = "fit";
    public static final String OPT_TEXTUREMODE_FULL = "full";
    public static final String OPT_TEXTUREMODE_CROP = "crop";

    protected HashMap<String, String> structureConfig;

    public Structure(HashMap<String, String> definition) {
        structureConfig = definition;
    }

    private String get(String var, String defaultVal) {
        if(!structureConfig.containsKey(var)) {
            return defaultVal;
        }
        return structureConfig.get(var);
    }

    private String get(String var) {
       return get(var, null);
    }

    public Image getTexture() throws RenderException {
        if(!doRender())
            throw new RenderException("Structure should not be rendered. Do not request a texture for it!");

        String textureFile = get("texture");

        if(textureFile == null)
            throw new RenderException("No texture applied to structure!");

        try {
            return new Image(textureFile);
        } catch (SlickException e) {
            throw new RenderException(e);
        }
    }

    public boolean doRender() {
        return get("render", OPT_RENDER).equals(OPT_RENDER);
    }

    public String getTextureMode() {
        return get("texturemode", OPT_TEXTUREMODE_FIT);
    }
}
