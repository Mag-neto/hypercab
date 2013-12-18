package de.mag.hypercab.app.hyperpin.filesystem;

import java.io.File;
import java.io.FilenameFilter;

public class FileFileNameFilter implements FilenameFilter {

	@Override
	public boolean accept(File dir, String name) {
		File file = new File(dir, name);
		if (file.isFile()) {
			return true;
		}
		return false;
	}

}
