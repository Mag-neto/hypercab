package de.mag.hypercab.api.ini;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

public class IniFileTest {

	private IniFile regularIniFile;
	private IniFile registryFile;

	@Test
	public void initializesRegularIniFile() throws IOException {
		regularIniFile = IniFileFactory.createIniFile("target/test-classes/Settings.ini",
				IniFileType.INI);
		Assert.assertEquals(regularIniFile.getSections().size(), 16);
	}

	@Test
	public void initializesRegistryIniFile() throws IOException {
		registryFile = IniFileFactory
				.createIniFile("target/test-classes/vpinmame.reg", IniFileType.REG);
		Assert.assertEquals(registryFile.getSections().size(), 52);
	}

	@Test(dependsOnMethods = { "initializesRegistryIniFile" })
	public void findsRegistryFileSectionByEnding() {
		SectionVO afmConfig = registryFile.getSectionEndingWith("afm_113b");
		Assert.assertNotNull(afmConfig);
		Assert.assertEquals(afmConfig.getConfigs().size(), 97);
	}

	@Test(dependsOnMethods = { "initializesRegularIniFile" })
	public void findsRegularSectionByName() {
		SectionVO videoSection = regularIniFile.getSection("Video");
		Assert.assertNotNull(videoSection);
		Assert.assertEquals(videoSection.getConfigs().size(), 13);
	}

}
