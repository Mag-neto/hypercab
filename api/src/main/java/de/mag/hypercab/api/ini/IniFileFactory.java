package de.mag.hypercab.api.ini;

import java.io.File;
import java.io.IOException;

public class IniFileFactory {

	public static IniFile createIniFile(String fileRef, IniFileType type) throws IOException {
		switch (type) {
		case INI:
			return new RegularIniFile(new File(fileRef));
		case REG:
			return new RegistryFile(new File(fileRef));
		default:
			throw new IllegalArgumentException("No such IniFile type " + type);
		}
	}

}
