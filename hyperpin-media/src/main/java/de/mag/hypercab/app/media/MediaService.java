package de.mag.hypercab.app.media;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

import de.mag.hypercab.app.hyperpin.config.Configuration;
import de.mag.hypercab.app.media.zip.Zip;

@Service
public class MediaService {

	@Resource
	private Configuration configuration;

	@Resource
	private ImageConverter imageConverter;

	@Resource
	private MediaPathResolver mediaPathResolver;

	@Resource
	private MediaPackInstaller mediaPackInstaller;

	public byte[] getImageData(String tableRef, MediaType type) {
		return imageConverter.toByteArray(mediaPathResolver.resolveMediaPath(tableRef, type));
	}

	public void storeMediaFile(InputStream fileData, String tableRef, MediaType type)
			throws IOException {
		File targetFile = mediaPathResolver.resolveMediaPath(tableRef, type);
		copySourceStreamToTargetFile(fileData, targetFile);
	}

	private void copySourceStreamToTargetFile(InputStream sourceStream, File targetFile)
			throws IOException {
		try (OutputStream out = new FileOutputStream(targetFile, false)) {
			IOUtils.copyLarge(sourceStream, out);
		} finally {
			IOUtils.closeQuietly(sourceStream);
		}
	}

	public boolean mediaFileExists(String tableRef, MediaType type) {
		return mediaPathResolver.resolveMediaPath(tableRef, type).exists();
	}

	public void storeTableFile(InputStream fileData, String fileName) throws IOException {
		String baseFileName = FilenameUtils.getBaseName(fileName);
		File targetFile = mediaPathResolver.resolveMediaPath(baseFileName, MediaType.VP_TABLE_FILE);
		copySourceStreamToTargetFile(fileData, targetFile);
	}

	public void storeMediaPack(InputStream inputStream, String originalFilename) throws IOException {
		File tempFile = storeToTempFile(inputStream, originalFilename);
		Zip.unzip(tempFile, tempFile.getParentFile());
		mediaPackInstaller.installFromDirectory(new File(tempFile.getParentFile(), "HyperPin"));
	}

	private File storeToTempFile(InputStream inputStream, String originalFilename) throws IOException {
		File tempFolder = createTempFolderForFile(originalFilename);
		File tempFile = new File(tempFolder, originalFilename);
		copySourceStreamToTargetFile(inputStream, tempFile);
		return tempFile;
	}

	private File createTempFolderForFile(String fileName) {
		File tempFolder = new File(configuration.getHyperCabTempPath(), fileName
				+ UUID.randomUUID());
		tempFolder.mkdirs();
		return tempFolder;
	}

}
