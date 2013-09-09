package de.mag.hypercab.app.hyperpin;

import java.io.File;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Configuration {

	@Value("${hyperpin.base.path}")
	private String hyperPinBasePath;

	public File getHyperpinPath() {
		return new File(hyperPinBasePath);
	}
}
