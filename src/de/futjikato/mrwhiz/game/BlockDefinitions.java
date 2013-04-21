package de.futjikato.mrwhiz.game;

import com.google.gson.Gson;
import de.futjikato.mrwhiz.rendering.Structure;

import java.io.*;
import java.util.HashMap;
import java.util.List;

public class BlockDefinitions {

	public static final String TYPE_BLOCK = "block";
	public static final String TYPE_ITEM = "item";
	public static final String TYPE_DOOR = "door";

    public HashMap<String, String> config = new HashMap<String, String>();

    public HashMap<Integer, HashMap<String, String>> definitions = new HashMap<Integer, HashMap<String, String>>();

    public HashMap<String, List<List<Integer>>> names = new HashMap<String, List<List<Integer>>>();

    public HashMap<String, List<List<Integer>>> routes = new HashMap<String, List<List<Integer>>>();

    public static BlockDefinitions create(File defineFile) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(defineFile));
        Gson gson = new Gson();
        return gson.fromJson(bufferedReader, BlockDefinitions.class);
    }

    public Structure getStructure(int id) {
        HashMap<String, String> definition = definitions.get(id);
        return new Structure(definition);
    }
}
