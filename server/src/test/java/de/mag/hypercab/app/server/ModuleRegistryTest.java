package de.mag.hypercab.app.server;

import javax.annotation.Resource;

import org.testng.Assert;
import org.testng.annotations.Test;

import de.mag.hypercab.AbstractIntegrationTest;

public class ModuleRegistryTest extends AbstractIntegrationTest {

	@Resource
	private ServerRegistry serverRegistry;

	@Test
	public void registryRegistersTestModules() {
		Assert.assertTrue(serverRegistry.getRegisteredModules().size() >= 1);
	}

	@Test
	public void registryRegistersTestEnhancers() {
		Assert.assertTrue(serverRegistry.getTableEnhancers().size() >= 1);
	}

}
