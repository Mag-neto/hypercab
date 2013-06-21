package de.mag.hypercab;

import java.io.File;

import javax.annotation.Resource;

import org.testng.Assert;
import org.testng.annotations.Test;

import de.mag.hypercab.app.server.Configuration;

public class ConfigurationTest extends AbstractIntegrationTest {

	@Resource
	private Configuration configuration;

	@Test
	public void configurationValuesAreReplaced() {
		File hyperpinBasePath = configuration.getHyperpinPath();
		Assert.assertTrue(hyperpinBasePath.isDirectory());
	}
}
