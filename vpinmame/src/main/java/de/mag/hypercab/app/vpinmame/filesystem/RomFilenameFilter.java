package de.mag.hypercab.app.vpinmame.filesystem;

import java.io.File;
import java.io.FilenameFilter;

import org.springframework.stereotype.Component;

@Component
public class RomFilenameFilter implements FilenameFilter {

	@Override
	public boolean accept(File dir, String name) {
		if (name.endsWith(".zip") && !name.startsWith(".")) {
			return true;
		}
		File testFile = new File(dir, name);
		if (testFile.isDirectory()) {
			return true;
		}
		return false;
	}

}
