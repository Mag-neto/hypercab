package de.mag.hypercab.api.ini;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.ini4j.Reg;

public class RegistryFile extends AbstractIniFile<Reg> {

	RegistryFile(File iniFile) throws IOException {
		super(iniFile);
	}

	@Override
	protected Reg createIniFile(File iniFile2) throws IOException {
		return new Reg(iniFile2);
	}

	@Override
	public void saveSections(List<SectionVO> sections) throws IOException {
		for (SectionVO section : sections) {
			writeSection(section);
			iniFile.store();
		}
	}

	@Override
	public void saveSection(SectionVO section) throws IOException {
		writeSection(section);
		iniFile.store();
	}

}
