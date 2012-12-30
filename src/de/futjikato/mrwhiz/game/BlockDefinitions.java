package de.futjikato.mrwhiz.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import de.futjikato.mrwhiz.xml.BlockCollector;

public class BlockDefinitions {

	public static final String TYPE_BLOCK = "block";
	public static final String TYPE_ITEM = "item";
	public static final String TYPE_DOOR = "door";

	private HashMap<Character, HashMap<String, String>> definitions = new HashMap<Character, HashMap<String, String>>();

	private HashMap<String, List<List<Integer>>> names = new HashMap<String, List<List<Integer>>>();

	private HashMap<String, List<List<Integer>>> routes = new HashMap<String, List<List<Integer>>>();

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
		return getTexture(type, "texture");
	}

	public Image getTexture(char type, String fieldname) {
		String value = getBlockAttributeAsString(type, fieldname);
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

	public List<Block> getNamedBlocks(String name) {
		List<List<Integer>> blockCoords = names.get(name);
		List<Block> resultList = new ArrayList<Block>();

		if (blockCoords == null) {
			return resultList;
		}

		for ( List<Integer> coords : blockCoords ) {
			int x = coords.get(0);
			int y = coords.get(1);

			Block cBlock = BlockCollector.getInstance().getBlock(x, y);

			if (cBlock != null && !resultList.contains(cBlock)) {
				resultList.add(cBlock);
			}
		}

		return resultList;
	}

	public Set<String> getNames() {
		return names.keySet();
	}

	public List<List<Integer>> getRoute(String name) {
		return routes.get(name);
	}

	public Set<String> getRoutes() {
		return routes.keySet();
	}
}
