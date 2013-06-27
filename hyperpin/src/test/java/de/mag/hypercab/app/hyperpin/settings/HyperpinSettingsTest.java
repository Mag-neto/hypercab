package de.mag.hypercab.app.hyperpin.settings;

import javax.annotation.Resource;

import org.testng.Assert;
import org.testng.annotations.Test;

import de.mag.hypercab.AbstractIntegrationTest;
import de.mag.hypercab.app.hyperpin.settings.HyperpinSettings;

public class HyperpinSettingsTest extends AbstractIntegrationTest {

	@Resource
	private HyperpinSettings hyperpinSettings;

	@Test
	public void settingsAreInitialized() {
		Assert.assertEquals(hyperpinSettings.getSettings().size(), 16);
	}
}
