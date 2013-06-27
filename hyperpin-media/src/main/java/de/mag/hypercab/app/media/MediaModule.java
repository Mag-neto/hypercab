package de.mag.hypercab.app.media;

import org.springframework.stereotype.Component;

import de.mag.hypercab.api.HypercabModule;

@Component
public class MediaModule implements HypercabModule {

	@Override
	public String getName() {
		return "Hyperpin Media Module";
	}

	@Override
	public String getModuleVersion() {
		// TODO: dynamically configure module version
		return "1.0-SNAPSHOT";
	}

	@Override
	public String getModuleDescription() {
		return "Optional media module enabling access to Hyperpin table media files such as images,flyers and videos";
	}

}
