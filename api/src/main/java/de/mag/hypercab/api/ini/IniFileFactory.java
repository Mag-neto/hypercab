package de.mag.hypercab.api.ini;

import java.io.File;
import java.io.IOException;

public class IniFileFactory {

	public static IniFile createIniFile(File sourceFile, IniFileType type) throws IOException {
		switch (type) {
		case INI:
			return new RegularIniFile(sourceFile);
		case REG:
			return new RegistryFile(sourceFile);
		default:
			throw new IllegalArgumentException("No such IniFile type " + type);
		}
	}

}
