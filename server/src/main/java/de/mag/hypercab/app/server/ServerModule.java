package de.mag.hypercab.app.server;

import de.mag.hypercab.api.HypercabModule;

public class ServerModule implements HypercabModule {

	@Override
	public String getName() {
		return "Hypercab Server Module";
	}

	@Override
	public String getModuleVersion() {
		// TODO: dynamically configure module version
		return "1.0-SNAPSHOT";
	}

	@Override
	public String getModuleDescription() {
		return "Core server module providing access to the Hyperpin base configuration found in Settings.ini file.";
	}

}
