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
import de.mag.hypercab.app.hyperpin.config.Section;

@Service
public class VisualPinballFileSystemService implements FileSystemCRUDService {

	@Resource
	private Configuration configuration;

	private File vpBasePath;

	@PostConstruct
	void init() {
		String vpPath = configuration.getSetting(Section.VISUAL_PINBALL, "Path");
		this.vpBasePath = new File(vpPath);
	}

	@Override
	public List<String> getFiles() {
		String[] files = vpBasePath.list(new FileFileNameFilter());
		return Arrays.asList(files);
	}

	@Override
	public void addFile(InputStream fileStream, String fileName) {
		File targetFile = new File(vpBasePath, fileName);
		try {
			StreamUtils.copyStreamToFile(fileStream, targetFile);
		} catch (IOException e) {
			throw new FileSystemException(e);
		}
	}

	@Override
	public void removeFile(String fileName) {
		File fileToDelete = new File(vpBasePath, fileName);
		if (fileToDelete.isFile()) {
			FileUtils.deleteQuietly(fileToDelete);
		}
	}

	@Override
	public void renameFile(String originalFileName, String newFileName) {
		File source = new File(vpBasePath, originalFileName);
		File dest = new File(vpBasePath, newFileName);
		try {
			FileUtils.moveFile(source, dest);
		} catch (IOException e) {
			throw new FileSystemException(e);
		}
	}
}
