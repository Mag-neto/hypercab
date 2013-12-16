package de.mag.hypercab.app.vpinmame.registry;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import de.mag.hypercab.AbstractIntegrationTest;
import de.mag.hypercab.api.ini.SectionVO;

public class RegistryServiceTest extends AbstractIntegrationTest {

	@Resource
	private RegistryService registryService;

	@Test
	public void registryFileIsLoaded() {
		List<SectionVO> romSettings = registryService.getRomSettings();
		Assert.assertNotNull(romSettings);
		Assert.assertEquals(romSettings.size(), 51);
	}

	@Test
	public void getDefaultSettings() {
		SectionVO romSettings = registryService.getRomSettings("default");
		Assert.assertNotNull(romSettings);
		Assert.assertEquals(romSettings.getConfigs().size(), 17);
	}

	@Test
	public void updateRomSettings() throws IOException {
		SectionVO settings = registryService.getRomSettings("afm_113b");
		Assert.assertNotNull(settings);
		String dmdX = settings.getConfig("dmd_pos_x");
		Assert.assertEquals(dmdX, "1969");
		settings.setConfig("dmd_pos_x", "1024");
		dmdX = settings.getConfig("dmd_pos_x");
		Assert.assertEquals(dmdX, "1024");
		settings.setConfig("dmd_pos_x", "1969");
	}

	@Test
	public void createsNewSectionForNewRom() throws IOException {
		String romName = RandomStringUtils.randomAlphabetic(6);
		SectionVO romSettings = registryService.getRomSettings(romName);
		Assert.assertNotNull(romSettings);
		Assert.assertTrue(romSettings.getName().contains(romName));
	}

}
