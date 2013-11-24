package de.mag.hypercab.app.vpinmame.registry;

public class OSUtils {

	static boolean isWindows() {
		String OS = System.getProperty("os.name").toLowerCase();
		return (OS.indexOf("win") >= 0);
	}
}