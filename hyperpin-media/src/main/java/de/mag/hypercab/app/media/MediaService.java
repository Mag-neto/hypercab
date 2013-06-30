package de.mag.hypercab.app.media;

import java.io.File;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import de.mag.hypercab.app.Configuration;

@Service
public class MediaService {

	@Resource
	private Configuration configuration;

	@Resource
	private ImageConverter imageConverter;

	public byte[] getImageData(String tableRef, MediaType type) {
		return imageConverter.toByteArray(resolveResourceFile(tableRef, type));
	}

	public boolean mediaFileExists(String tableRef, MediaType type) {
		return resolveResourceFile(tableRef, type).exists();
	}

	private File resolveResourceFile(String tableRef, MediaType type) {
		// FIXME: Flyer Images are in subfolders (Front,Back,Inside1...)
		return new File(configuration.getHyperpinPath(), type.mediaPath() + tableRef + type.extension());
	}
}
