package de.futjikato.mrwhiz;

import org.lwjgl.Sys;

public final class Util {

	private Util() {
	}

	public static long getTime() {
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}
}
