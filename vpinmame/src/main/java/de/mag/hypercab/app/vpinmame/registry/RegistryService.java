package de.mag.hypercab.app.vpinmame.registry;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import de.mag.hypercab.api.ini.IniFile;
import de.mag.hypercab.api.ini.IniFileFactory;
import de.mag.hypercab.api.ini.IniFileType;
import de.mag.hypercab.api.ini.SectionVO;
import de.mag.hypercab.app.hyperpin.config.Configuration;

@Service
public class RegistryService {

	private static Logger LOGGER = LoggerFactory.getLogger(RegistryService.class);

	private static final String WORKDIR_NAME = "vpinmame";
	private static final String REG_FILENAME = "vpinmame.reg";
	private static final String REGISTRY_KEY = "\"HKEY_CURRENT_USER\\Software\\Freeware\\Visual PinMame\"";

	@Resource
	private Configuration configuration;

	private File workingDir;
	private File registrySourceFile;
	private IniFile registryFile;

	@PostConstruct
	void init() throws IOException {
		createWorkingDirectory();
		this.registrySourceFile = new File(workingDir, REG_FILENAME);
		LOGGER.debug("Registry file is: " + registrySourceFile);
		if (OSUtils.isWindows()) {
			RegistryUtils.exportRegistryTree(REGISTRY_KEY, registrySourceFile);
		}
		this.registryFile = IniFileFactory.createIniFile(registrySourceFile, IniFileType.REG);
	}

	private void createWorkingDirectory() {
		this.workingDir = new File(configuration.getHyperCabTempPath(), WORKDIR_NAME);
		this.workingDir.mkdirs();
	}

	public void updateRomSettings(SectionVO romSection) throws IOException {
		this.registryFile.saveSection(romSection);
	}

	public void updateRomSettings(List<SectionVO> romSections) throws IOException {
		this.registryFile.saveSections(romSections);
	}

	public List<SectionVO> getRomSettings() {
		return this.registryFile.getSections();
	}

	public SectionVO getRomSettings(String romName) {
		return this.registryFile.getSectionEndingWith(romName);
	}

	public void writeRegistry() throws IOException {
		registryFile.saveSections(registryFile.getSections());
		if (OSUtils.isWindows()) {
			RegistryUtils.importRegistryTree(registrySourceFile);
		}
	}
}
