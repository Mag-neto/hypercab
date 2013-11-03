package de.mag.hypercab.app.hyperpin;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import de.mag.hypercab.api.ini.IniFile;
import de.mag.hypercab.api.ini.IniFileFactory;
import de.mag.hypercab.api.ini.IniFileType;
import de.mag.hypercab.api.ini.SectionVO;

@Component
public class Configuration {

	private static final String SETTINGS_SUBPATH = "Settings/Settings.ini";

	@Value("${hyperpin.base.path}")
	private String hyperPinBasePath;

	private IniFile settings;

	@PostConstruct
	public void init() throws IOException {
		File settingsFile = new File(hyperPinBasePath, SETTINGS_SUBPATH);
		this.settings = IniFileFactory.createIniFile(settingsFile.getAbsolutePath(), IniFileType.INI);
	}

	public List<SectionVO> getSettings() {
		return settings.getSections();
	}

	public void saveSettings(List<SectionVO> configs) throws IOException {
		settings.saveSections(configs);
	}

	public File getHyperpinPath() {
		return new File(hyperPinBasePath);
	}
}
