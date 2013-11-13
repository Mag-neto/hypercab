package de.mag.hypercab.app.hyperpin;

import java.io.File;

import javax.annotation.Resource;

import org.testng.Assert;
import org.testng.annotations.Test;

import de.mag.hypercab.AbstractIntegrationTest;
import de.mag.hypercab.app.hyperpin.config.Configuration;
import de.mag.hypercab.app.hyperpin.config.Section;

public class ConfigurationTest extends AbstractIntegrationTest {

	@Resource
	private Configuration configuration;

	@Test
	public void settingsAreInitialized() {
		Assert.assertEquals(configuration.getSettings().size(), 16);
	}

	@Test
	public void singleSettingIsResolved() {
		String setting = configuration.getSetting(Section.VISUAL_PINBALL, "Backglass_Image_Path");
		Assert.assertNotNull(setting);
		Assert.assertEquals(setting, "C:" + File.separator + "HyperPin" + File.separator + "Media" + File.separator
				+ "Visual Pinball" + File.separator + "Backglass Images" + File.separator);
	}
}
