package de.mag.hypercab.api.filesystem;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractFileSystemCRUDService implements FileSystemCRUDService {
	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractFileSystemCRUDService.class);

	protected File basePath;
	protected FilenameFilter filter;

	@Override
	public List<String> getFiles() {
		String[] files = basePath.list(filter);
		return Arrays.asList(files);
	}

	@Override
	public void addFile(InputStream fileStream, String fileName) {
		File targetFile = new File(basePath, fileName);
		try {
			StreamUtils.copyStreamToFile(fileStream, targetFile);
		} catch (IOException e) {
			throw new FileSystemException(e);
		}
	}

	@Override
	public void removeFile(String fileName) {
		File fileToDelete = new File(basePath, fileName);
		LOGGER.debug("File to delete is " + fileToDelete.getAbsolutePath());
		if (fileToDelete.isFile()) {
			try {
				FileUtils.forceDelete(fileToDelete);
				LOGGER.debug("Deleted file " + fileToDelete.getName());
			} catch (IOException e) {
				throw new FileSystemException(e);
			}
		}
	}

	@Override
	public void renameFile(String originalFileName, String newFileName) {
		File source = new File(basePath, originalFileName);
		File dest = new File(basePath, newFileName);
		try {
			FileUtils.moveFile(source, dest);
		} catch (IOException e) {
			throw new FileSystemException(e);
		}
	}

}
