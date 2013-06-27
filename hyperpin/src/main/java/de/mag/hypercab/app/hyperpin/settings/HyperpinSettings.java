package de.mag.hypercab.app.hyperpin.settings;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.ini4j.Ini;
import org.ini4j.Profile.Section;
import org.springframework.stereotype.Component;

import de.mag.hypercab.app.Configuration;

@Component
public class HyperpinSettings {

	private static final String SETTINGS_SUBPATH = "Settings/Settings.ini";

	@Resource
	private Configuration configuration;

	private Ini settings;

	@PostConstruct
	public void init() throws IOException {
		File settingsFile = new File(configuration.getHyperpinPath(), SETTINGS_SUBPATH);
		settings = new Ini(settingsFile);
	}

	public Map<String, Map<String, String>> getSettings() {
		Map<String, Map<String, String>> sectionData = new HashMap<>();
		Set<Entry<String, Section>> entrySet = settings.entrySet();
		for (Entry<String, Section> entry : entrySet) {
			Set<Entry<String, String>> configEntries = entry.getValue().entrySet();
			sectionData.put(entry.getKey(), extractSectionConfig(configEntries));

		}
		return sectionData;
	}

	private Map<String, String> extractSectionConfig(Set<Entry<String, String>> configEntries) {
		Map<String, String> sectionConfig = new HashMap<>();
		for (Entry<String, String> e : configEntries) {
			sectionConfig.put(e.getKey(), e.getValue());
		}
		return sectionConfig;
	}

	public void saveSettings(Map<String, Map<String, String>> config) throws IOException {
		for (Entry<String, Map<String, String>> section : config.entrySet()) {
			writeSectionConfig(section);
		}
		settings.store();
	}

	private void writeSectionConfig(Entry<String, Map<String, String>> section) {
		Section currentSection = settings.get(section.getKey());
		for (Entry<String, String> configValue : section.getValue().entrySet()) {
			currentSection.put(configValue.getKey(), configValue.getValue());
		}
	}

}
