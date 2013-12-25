package de.mag.hypercab.app.hyperpin.filesystem;

import java.io.File;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import de.mag.hypercab.api.filesystem.AbstractFileSystemCRUDService;
import de.mag.hypercab.app.hyperpin.config.Configuration;
import de.mag.hypercab.app.hyperpin.config.Section;

@Service
public class VisualPinballFileSystemService extends AbstractFileSystemCRUDService {

	@Resource
	private Configuration configuration;

	@PostConstruct
	void init() {
		String vpPath = configuration.getSetting(Section.VISUAL_PINBALL, "Path");
		this.basePath = new File(vpPath);
		this.filter = new FileFileNameFilter();
	}

}
