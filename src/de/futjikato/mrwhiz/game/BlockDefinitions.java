package de.futjikato.mrwhiz.game;

import java.util.HashMap;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class BlockDefinitions {

	public static final String TYPE_BLOCK = "block";
	public static final String TYPE_ITEM = "item";

	private HashMap<Character, HashMap<String, String>> definitions = new HashMap<Character, HashMap<String, String>>();

	public boolean isDefined(char type) {
		return definitions.containsKey(type);
	}

	public String getType(char type) {
		String strVal = getBlockAttributeAsString(type, "type");
		if (strVal == null) {
			return TYPE_BLOCK;
		}
		return strVal;
	}

	public String getBlockAttributeAsString(char type, String attribute) {
		if (!isDefined(type)) {
			return null;
		}
		HashMap<String, String> attributes = definitions.get(type);

		if (!attributes.containsKey(attribute)) {
			return null;
		}

		return attributes.get(attribute);
	}

	public int getBlockAttributeAsInt(char type, String attribute, int defaultVal) {
		String strVal = getBlockAttributeAsString(type, attribute);

		if (strVal == null) {
			return defaultVal;
		}

		return Integer.valueOf(strVal);
	}

	public boolean getBlockAttributeAsBoolean(char type, String attribute, boolean defaultVal) {
		int intDefault = 0;
		if (defaultVal) {
			intDefault = 1;
		}

		int intVal = getBlockAttributeAsInt(type, attribute, intDefault);

		return intVal == 1;
	}

	public Image getTexture(char type) {
		String value = getBlockAttributeAsString(type, "texture");
		if (value == null) {
			return null;
		}

		try {
			Image img = new Image(value);
			return img;
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
