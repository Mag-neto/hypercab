package de.mag.hypercab.app.media;

import java.io.File;
import java.io.IOException;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;

import de.mag.hypercab.app.hyperpin.config.Configuration;

@Component
public class MediaPackInstaller {

	@Resource
	private Configuration configuration;

	void installFromDirectory(File directory) throws IOException {
		if (directory.getName() != "HyperPin") {
			throw new IllegalArgumentException("Given directory is not a media pack: "
					+ directory.getAbsolutePath());
		}
		FileUtils.copyDirectory(directory, configuration.getHyperpinPath());
	}

}
