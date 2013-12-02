package de.mag.hypercab.api.ini;

import java.io.File;
import java.io.IOException;

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
	protected void storeToFile() throws IOException {
		iniFile.store();
	}

}
