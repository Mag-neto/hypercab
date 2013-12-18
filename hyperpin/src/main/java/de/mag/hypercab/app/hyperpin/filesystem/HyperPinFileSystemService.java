package de.mag.hypercab.app.hyperpin.filesystem;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import de.mag.hypercab.api.filesystem.FileSystemCRUDService;
import de.mag.hypercab.api.filesystem.FileSystemException;
import de.mag.hypercab.api.filesystem.StreamUtils;
import de.mag.hypercab.app.hyperpin.config.Configuration;

@Service
public class HyperPinFileSystemService implements FileSystemCRUDService {

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
		File fileToDelete = new File(hyperPinPath, fileName);
		if (fileToDelete.isFile()) {
			FileUtils.deleteQuietly(fileToDelete);
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
