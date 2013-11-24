package de.mag.hypercab.app.vpinmame;

import org.springframework.stereotype.Component;

import de.mag.hypercab.api.HypercabModule;

@Component
public class VpinMameModule implements HypercabModule {

	@Override
	public String getName() {
		return "VPinMame Module";
	}

	@Override
	public String getModuleVersion() {
		return "1.0-SNAPSHOT";
	}

	@Override
	public String getModuleDescription() {
		return "Optional module allowing rom management and adjustment";
	}

}
