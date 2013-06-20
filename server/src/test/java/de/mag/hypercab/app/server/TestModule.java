package de.mag.hypercab.app.server;

import org.springframework.stereotype.Component;

import de.mag.hypercab.api.HypercabModule;

@Component
public class TestModule implements HypercabModule {

	@Override
	public String getModuleVersion() {
		return "TEST-1.0";
	}

	@Override
	public String getName() {
		return "Test-Module-1";
	}

	@Override
	public String getModuleDescription() {
		return "Test module for unit testing";
	}

}
