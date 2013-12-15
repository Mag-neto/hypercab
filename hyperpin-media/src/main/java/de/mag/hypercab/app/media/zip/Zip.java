package de.mag.hypercab.app.media.zip;

import java.io.File;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;

public class Zip {

	public static void unzip(File archive, File destination) {
		try {
			ZipFile zipArchive = new ZipFile(archive);
			zipArchive.extractAll(destination.getAbsolutePath());
		} catch (ZipException e) {
			throw new ArchiveException(e);
		}
	}
}
