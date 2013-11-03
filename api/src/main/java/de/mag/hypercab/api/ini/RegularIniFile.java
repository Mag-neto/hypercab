package de.mag.hypercab.api.ini;

import java.io.File;
import java.io.IOException;

import org.ini4j.Ini;

public class RegularIniFile extends AbstractIniFile {

	RegularIniFile(File iniFile) throws IOException {
		super(iniFile);
	}

	@Override
	protected Ini createIniFile(File iniFile2) throws IOException {
		return new Ini(iniFile2);
	}

}
