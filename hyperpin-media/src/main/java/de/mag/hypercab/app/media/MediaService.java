package de.mag.hypercab.app.media;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.annotation.Resource;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

import de.mag.hypercab.app.hyperpin.Configuration;

@Service
public class MediaService {

	@Resource
	private Configuration configuration;

	@Resource
	private ImageConverter imageConverter;

	public byte[] getImageData(String tableRef, MediaType type) {
		return imageConverter.toByteArray(resolveResourceFile(tableRef, type));
	}

	public void storeMediaFile(InputStream fileData, String tableRef, MediaType type) throws IOException {
		File targetFile = resolveResourceFile(tableRef, type);
		try (OutputStream out = new FileOutputStream(targetFile, false)) {
			IOUtils.copy(fileData, out);
		} finally {
			IOUtils.closeQuietly(fileData);
		}
	}

	public boolean mediaFileExists(String tableRef, MediaType type) {
		return resolveResourceFile(tableRef, type).exists();
	}

	private File resolveResourceFile(String tableRef, MediaType type) {
		// FIXME: Flyer Images are in subfolders (Front,Back,Inside1...)
		return new File(configuration.getHyperpinPath(), type.mediaPath() + tableRef + type.extension());
	}
}
