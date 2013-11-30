package de.mag.hypercab.api.ini;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.ini4j.Ini;

public class RegularIniFile extends AbstractIniFile<Ini> {

	RegularIniFile(File iniFile) throws IOException {
		super(iniFile);
	}

	@Override
	protected Ini createIniFile(File iniFile2) throws IOException {
		return new Ini(iniFile2);
	}

	@Override
	public void saveSections(List<SectionVO> sections) throws IOException {
		for (SectionVO section : sections) {
			writeSection(section);
		}
		iniFile.store();
	}

	@Override
	public void saveSection(SectionVO section) throws IOException {
		writeSection(section);
		iniFile.store();
	}

}
