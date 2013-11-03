package de.mag.hypercab.api.ini;

import java.io.IOException;
import java.util.List;

public interface IniFile {

	List<SectionVO> getSections();

	void saveSections(List<SectionVO> sections) throws IOException;

	SectionVO getSection(String sectionName);

	SectionVO getSectionEndingWith(String sectionNameEnding);

	void saveSection(SectionVO section) throws IOException;

}
