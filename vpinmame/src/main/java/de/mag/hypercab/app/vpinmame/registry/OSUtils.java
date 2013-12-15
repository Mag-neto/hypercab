package de.mag.hypercab.app.vpinmame.registry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OSUtils {

	private static Logger LOGGER = LoggerFactory.getLogger(OSUtils.class);

	static boolean isWindows() {
		String OS = System.getProperty("os.name").toLowerCase();
		LOGGER.debug("OS string is: " + OS);
		return (OS.indexOf("win") >= 0);
	}
}