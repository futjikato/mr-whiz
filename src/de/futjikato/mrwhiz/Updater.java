package de.futjikato.mrwhiz;

import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Updater {

	private static String infoUrl = "http://futjikato.koding.com/whiz/update.json";

	private long latestVersion;

	private List<UpdaterNews> newsList = new ArrayList<UpdaterNews>();

	public Updater() {
		try {
			this.loadInformations();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void loadInformations() throws Exception {
		StringBuilder buf = new StringBuilder();

		URL url = new URL(infoUrl);
		URLConnection con = url.openConnection();
		Reader r = new InputStreamReader(con.getInputStream(), "UTF-8");

		int ch;
		while ((ch = r.read()) > 0) {
			buf.append((char) ch);
		}

		if (buf.length() > 0) {
			JSONParser parser = new JSONParser();
			Object res = parser.parse(buf.toString());

			if (!(res instanceof JSONObject)) {
				throw new Exception("Invalid JSON result.");
			}

			JSONObject jsonobj = (JSONObject) res;

			this.latestVersion = (Long) jsonobj.get("latestVersion");

			Object newsContainer = jsonobj.get("news");
			if (newsContainer instanceof JSONArray) {
				JSONArray newsArray = (JSONArray) newsContainer;
				for ( Object newsEntry : newsArray ) {
					if (newsEntry instanceof JSONObject) {
						newsList.add(new UpdaterNews((JSONObject) newsEntry));
					}
				}
			}
		}
	}

	public boolean checkForUpdates(long currentVersion) {
		if (this.latestVersion > 0) {
			return this.latestVersion > currentVersion;
		}
		return false;
	}

	public List<UpdaterNews> getNewsList() {
		return this.newsList;
	}
}
