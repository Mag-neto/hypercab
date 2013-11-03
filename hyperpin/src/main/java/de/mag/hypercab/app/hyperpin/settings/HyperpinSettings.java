package de.mag.hypercab.app.hyperpin.settings;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import de.mag.hypercab.api.ini.IniFile;
import de.mag.hypercab.api.ini.IniFileFactory;
import de.mag.hypercab.api.ini.IniFileType;
import de.mag.hypercab.api.ini.SectionVO;
import de.mag.hypercab.app.hyperpin.Configuration;

@Component
public class HyperpinSettings {

	private static final String SETTINGS_SUBPATH = "Settings/Settings.ini";

	@Resource
	private Configuration configuration;

	private IniFile iniFile;

	@PostConstruct
	public void init() throws IOException {
		File settingsFile = new File(configuration.getHyperpinPath(), SETTINGS_SUBPATH);
		this.iniFile = IniFileFactory.createIniFile(settingsFile.getAbsolutePath(), IniFileType.INI);
	}

	public List<SectionVO> getSettings() {
		return iniFile.getSections();
	}

	public void saveSettings(List<SectionVO> configs) throws IOException {
		iniFile.saveSections(configs);
	}

}
