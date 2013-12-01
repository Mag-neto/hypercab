package de.mag.hypercab.app.vpinmame.registry;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

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

}
