package de.mag.hypercab.app;

import org.springframework.stereotype.Component;

import de.mag.hypercab.api.HypercabModule;

@Component
public class TestModule implements HypercabModule {

	@Override
	public String getName() {
		return null;
	}

	@Override
	public String getModuleVersion() {
		return null;
	}

	@Override
	public String getModuleDescription() {
		return null;
	}

}
