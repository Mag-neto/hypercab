package de.mag.hypercab.api.ini;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.ini4j.BasicProfile;
import org.ini4j.Profile.Section;

public abstract class AbstractIniFile<E extends BasicProfile> implements IniFile {

	protected E iniFile;

	AbstractIniFile(File iniFile) throws IOException {
		this.iniFile = createIniFile(iniFile);
	}

	protected abstract E createIniFile(File iniFile2) throws IOException;

	@Override
	public List<SectionVO> getSections() {
		List<SectionVO> sections = new ArrayList<>();
		Set<Entry<String, Section>> entrySet = iniFile.entrySet();
		for (Entry<String, Section> entry : entrySet) {
			SectionVO s = createSectionVO(entry.getKey(), entry.getValue());
			sections.add(s);
		}
		return sections;
	}

	private SectionVO createSectionVO(String sectionName, Section section) {
		SectionVO s = new SectionVO();
		s.setName(sectionName);
		s.setConfigs(extractConfigs(section.entrySet()));
		return s;
	}

	private List<KeyValuePair> extractConfigs(Set<Entry<String, String>> configEntries) {
		List<KeyValuePair> configs = new ArrayList<>();
		for (Entry<String, String> e : configEntries) {
			configs.add(new KeyValuePair(e.getKey(), e.getValue()));
		}
		return configs;
	}

	@Override
	public void saveSections(List<SectionVO> sections) throws IOException {
		for (SectionVO section : sections) {
			writeSection(section);
		}
		storeToFile();
	}

	private void writeSection(SectionVO section) {
		Section currentSection = iniFile.get(section.getName());
		if (currentSection == null) {
			currentSection = iniFile.add(section.getName());
		}
		for (KeyValuePair config : section.getConfigs()) {
			currentSection.put(config.getKey(), config.getValue());
		}
	}

	protected abstract void storeToFile() throws IOException;

	@Override
	public SectionVO getSection(String sectionName) {
		Section section = iniFile.get(sectionName);
		SectionVO s = createSectionVO(sectionName, section);
		return s;
	}

	@Override
	public SectionVO getSectionEndingWith(String sectionNameEnding) {
		Set<Entry<String, Section>> entrySet = iniFile.entrySet();
		for (Entry<String, Section> entry : entrySet) {
			if (entry.getKey().endsWith(sectionNameEnding)) {
				return createSectionVO(entry.getKey(), entry.getValue());
			}
		}
		return null;
	}

	@Override
	public void saveSection(SectionVO section) throws IOException {
		writeSection(section);
		storeToFile();
	}
}
