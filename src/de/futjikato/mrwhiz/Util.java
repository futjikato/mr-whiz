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

	public static List<ResolutionListEntry> getSystemResolutionList() {
		List<ResolutionListEntry> list = new ArrayList<ResolutionListEntry>();

		try {
			for ( DisplayMode mode : Display.getAvailableDisplayModes() ) {
				ResolutionListEntry resEntry = new ResolutionListEntry(mode);
				list.add(resEntry);
			}
		} catch (LWJGLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}
}
