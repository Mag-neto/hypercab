package de.mag.hypercab.app.hyperpin;

import javax.annotation.Resource;

import org.testng.Assert;
import org.testng.annotations.Test;

import de.mag.hypercab.AbstractIntegrationTest;
import de.mag.hypercab.app.hyperpin.Configuration;

public class ConfigurationTest extends AbstractIntegrationTest {

	@Resource
	private Configuration configuration;

	@Test
	public void settingsAreInitialized() {
		Assert.assertEquals(configuration.getSettings().size(), 16);
	}
}
