package de.mag.hypercab.app.hyperpin.filesystem;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import de.mag.hypercab.api.filesystem.AbstractFileSystemCRUDService;
import de.mag.hypercab.app.hyperpin.config.Configuration;

@Service
public class HyperPinFileSystemService extends AbstractFileSystemCRUDService {

	@Resource
	private Configuration configuration;

	@PostConstruct
	public void init() {
		this.basePath = configuration.getHyperpinPath();
		this.filter = new FileFileNameFilter();
	}

}
