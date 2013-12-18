package de.mag.hypercab.api.filesystem;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.IOUtils;

public class StreamUtils {

	public static void copyStreamToFile(InputStream sourceStream, File targetFile) throws IOException {
		try (OutputStream out = new FileOutputStream(targetFile, false)) {
			IOUtils.copyLarge(sourceStream, out);
		} finally {
			IOUtils.closeQuietly(sourceStream);
		}
	}
}
