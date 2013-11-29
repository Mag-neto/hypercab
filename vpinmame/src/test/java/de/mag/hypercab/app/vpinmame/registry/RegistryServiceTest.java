package de.mag.hypercab.app.vpinmame.registry;

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
		Assert.assertEquals(romSettings.size(), 52);
	}

	@Test
	public void getDefaultSettings() {
		SectionVO romSettings = registryService.getRomSettings("default");
		Assert.assertNotNull(romSettings);
		Assert.assertEquals(romSettings.getConfigs().size(), 17);
	}

}
