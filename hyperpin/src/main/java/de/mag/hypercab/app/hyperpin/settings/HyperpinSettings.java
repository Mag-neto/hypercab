package de.mag.hypercab.app.hyperpin.settings;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.ini4j.Ini;
import org.ini4j.Profile.Section;
import org.springframework.stereotype.Component;

import de.mag.hypercab.app.hyperpin.Configuration;

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

	public List<SectionVO> getSettings() {
		List<SectionVO> sections = new ArrayList<>();
		Set<Entry<String, Section>> entrySet = settings.entrySet();
		for (Entry<String, Section> entry : entrySet) {
			Set<Entry<String, String>> configEntries = entry.getValue().entrySet();
			SectionVO s = new SectionVO();
			s.setName(entry.getKey());
			s.setConfigs(extractConfigs(configEntries));
			sections.add(s);
		}
		return sections;
	}

	private List<KeyValuePair> extractConfigs(Set<Entry<String, String>> configEntries) {
		List<KeyValuePair> configs = new ArrayList<>();
		for (Entry<String, String> e : configEntries) {
			configs.add(new KeyValuePair(e.getKey(), e.getValue()));
		}
		return configs;
	}

	public void saveSettings(List<SectionVO> configs) throws IOException {
		for (SectionVO section : configs) {
			writeSectionConfig(section);
		}
		settings.store();
	}

	private void writeSectionConfig(SectionVO section) {
		Section currentSection = settings.get(section.getName());
		for (KeyValuePair config : section.getConfigs()) {
			currentSection.put(config.getKey(), config.getValue());
		}
	}

}
