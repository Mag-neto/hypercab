package de.mag.hypercab.api.ini;

import java.io.File;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

public class IniFileTest {

	private IniFile regularIniFile;
	private IniFile registryFile;

	@Test
	public void initializesRegularIniFile() throws IOException {
		regularIniFile = IniFileFactory.createIniFile(new File("target/test-classes/Settings.ini"),
				IniFileType.INI);
		Assert.assertEquals(regularIniFile.getSections().size(), 16);
	}

	@Test
	public void initializesRegistryIniFile() throws IOException {
		registryFile = IniFileFactory
				.createIniFile(new File("target/test-classes/vpinmame.reg"), IniFileType.REG);
		Assert.assertEquals(registryFile.getSections().size(), 51);
	}

	@Test(dependsOnMethods = { "initializesRegistryIniFile" })
	public void findsRegistryFileSectionByEnding() {
		SectionVO afmConfig = registryFile.getSectionEndingWith("afm_113b");
		Assert.assertNotNull(afmConfig);
		Assert.assertEquals(afmConfig.getConfigs().size(), 97);
	}

	@Test(dependsOnMethods = { "initializesRegistryIniFile" })
	public void addsNewSection() throws IOException {
		SectionVO sectionVO = registryFile.getSectionEndingWith("default");
		Assert.assertNotNull(sectionVO);
		String newName = sectionVO.getName().replace("default", "newSection");
		sectionVO.setName(newName);
		registryFile.saveSection(sectionVO);
		SectionVO newSection = registryFile.getSectionEndingWith("newSection");
		Assert.assertTrue(newSection.getName().endsWith("newSection"));
	}

	@Test(dependsOnMethods = { "initializesRegularIniFile" })
	public void findsRegularSectionByName() {
		SectionVO videoSection = regularIniFile.getSection("Video");
		Assert.assertNotNull(videoSection);
		Assert.assertEquals(videoSection.getConfigs().size(), 13);
	}

}
