package de.futjikato.mrwhiz;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import de.futjikato.mrwhiz.ui.ResolutionListEntry;

public final class Util {

	private Util() {
	}

	public static long getTime() {
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}

	public static String getUniqString() {
		int rand = (int) (Math.random() * 1000);
		return String.format("%d-%d", Util.getTime(), rand);
	}

	public static List<ResolutionListEntry> getSystemResolutionList() {
		List<ResolutionListEntry> list = new ArrayList<ResolutionListEntry>();

		try {
			for ( DisplayMode mode : Display.getAvailableDisplayModes() ) {

				boolean isProcessed = false;

				for ( ResolutionListEntry cEntry : list ) {
					if (cEntry.getHeight() == mode.getHeight() && cEntry.getWidth() == mode.getWidth()) {
						isProcessed = true;

						// add new entry to the list
						if (cEntry.getFreq() <= mode.getFrequency()) {
							// remove old entry
							list.remove(cEntry);

							// add new
							ResolutionListEntry resEntry = new ResolutionListEntry(mode);
							list.add(resEntry);

							break;
						}
					}
				}

				if (!isProcessed) {
					ResolutionListEntry resEntry = new ResolutionListEntry(mode);
					list.add(resEntry);
				}
			}
		} catch (LWJGLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}
}
