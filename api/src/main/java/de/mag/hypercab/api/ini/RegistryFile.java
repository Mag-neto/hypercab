package de.mag.hypercab.api.ini;

import java.io.File;
import java.io.IOException;

import org.ini4j.Ini;
import org.ini4j.Wini;

public class RegistryFile extends AbstractIniFile {

	RegistryFile(File iniFile) throws IOException {
		super(iniFile);
	}

	@Override
	protected Ini createIniFile(File iniFile2) throws IOException {
		return new Wini(iniFile2);
	}

}
