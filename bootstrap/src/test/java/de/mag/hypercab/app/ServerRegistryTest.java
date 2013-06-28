package de.mag.hypercab.app;

import javax.annotation.Resource;

import org.testng.Assert;
import org.testng.annotations.Test;

import de.mag.hypercab.AbstractIntegrationTest;

public class ServerRegistryTest extends AbstractIntegrationTest {

	@Resource
	private ServerRegistry serverRegistry;

	@Test
	public void registryRegistersTestModules() {
		Assert.assertEquals(serverRegistry.getRegisteredModules().size(), 1);
	}

	@Test
	public void registryRegistersTestEnhancers() {
		Assert.assertEquals(serverRegistry.getTableEnhancers().size(), 2);
	}

}
