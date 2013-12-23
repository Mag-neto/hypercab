package de.mag.hypercab.app.hyperpin.filesystem;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import de.mag.hypercab.api.filesystem.FileSystemCRUDService;
import de.mag.hypercab.api.filesystem.FileSystemException;
import de.mag.hypercab.api.filesystem.StreamUtils;
import de.mag.hypercab.app.hyperpin.config.Configuration;

@Service
public class HyperPinFileSystemService implements FileSystemCRUDService {

	private static final Logger LOGGER = LoggerFactory.getLogger(HyperPinFileSystemService.class);

	@Resource
	private Configuration configuration;

	private File hyperPinPath;

	@PostConstruct
	void init() {
		this.hyperPinPath = configuration.getHyperpinPath();
	}

	@Override
	public List<String> getFiles() {
		String[] files = hyperPinPath.list(new FileFileNameFilter());
		return Arrays.asList(files);
	}

	@Override
	public void addFile(InputStream fileStream, String fileName) {
		File targetFile = new File(hyperPinPath, fileName);
		try {
			StreamUtils.copyStreamToFile(fileStream, targetFile);
		} catch (IOException e) {
			throw new FileSystemException(e);
		}
	}

	@Override
	public void removeFile(String fileName) {
		LOGGER.debug("removeFile called with file name " + fileName);
		File fileToDelete = new File(hyperPinPath, fileName);
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
		File source = new File(hyperPinPath, originalFileName);
		File dest = new File(hyperPinPath, newFileName);
		try {
			FileUtils.moveFile(source, dest);
		} catch (IOException e) {
			throw new FileSystemException(e);
		}
	}

}
