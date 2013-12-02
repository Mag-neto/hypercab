package de.mag.hypercab.api.ini;

import java.io.File;
import java.io.IOException;

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
	protected void storeToFile() throws IOException {
		iniFile.store();
	}
}