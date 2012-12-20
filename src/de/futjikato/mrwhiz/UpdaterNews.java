package de.futjikato.mrwhiz;

import org.json.simple.JSONObject;

public class UpdaterNews {

	private String headline;
	private String copytext;

	public UpdaterNews(JSONObject base) {
		Object headlineObj = base.get("headline");
		if (headlineObj instanceof String) {
			this.headline = (String) headlineObj;
		}

		Object copyObj = base.get("copy");
		if (copyObj instanceof String) {
			this.copytext = (String) copyObj;
		}
	}

	public String getHeadline() {
		return headline;
	}

	public String getCopytext() {
		return copytext;
	}
}
