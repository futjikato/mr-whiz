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

    private Image texture;

    private boolean render = true;

    private String textureMode;

    public Structure(HashMap<String, String> definition) {
        structureConfig = definition;

        // set basic values
        render = get("render", OPT_RENDER).equals(OPT_RENDER);
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
        if(texture == null) {
            if(!doRender())
                throw new RenderException("Structure should not be rendered. Do not request a texture for it!");

            String textureFile = get("texture");

            if(textureFile == null)
                throw new RenderException("No texture applied to structure!");

            try {
                texture = new Image(textureFile);
            } catch (SlickException e) {
                throw new RenderException(e);
            }
        }

        return texture;
    }

    public boolean doRender() {
        return render;
    }

    public String getTextureMode() {
        if(textureMode == null) {
            textureMode = get("texturemode", OPT_TEXTUREMODE_FIT);
        }
        return textureMode;
    }
}
