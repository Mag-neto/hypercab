package de.mag.hypercab.app.hyperpin;

import org.springframework.stereotype.Component;

import de.mag.hypercab.api.HypercabModule;

@Component
public class HyperpinModule implements HypercabModule {

	@Override
	public String getName() {
		return "Hyperpin Module";
	}

	@Override
	public String getModuleVersion() {
		// TODO: dynamically configure module version
		return "1.0-SNAPSHOT";
	}

	@Override
	public String getModuleDescription() {
		return "Core module providing read/write access to Hyperpin databases and the Hyperpin Settings.ini file.";
	}

}
