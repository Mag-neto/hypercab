package de.mag.hypercab.app.vpinmame.registry;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RegistryUtils {

	static void exportRegistryTree(String key, File targetFile) {
		List<String> command = createRegeditExportCommand(key, targetFile);
		executeCommand(command);
	}

	private static List<String> createRegeditExportCommand(String key, File targetFile) {
		List<String> command = new ArrayList<>();
		command.add("regedit");
		command.add("/E");
		command.add(targetFile.getAbsolutePath());
		command.add(key);
		return command;
	}

	private static void executeCommand(List<String> command) {
		ProcessBuilder builder = new ProcessBuilder(command);
		try {
			Process process = builder.start();
			int status = process.waitFor();
			checkResultStatus(status);
		} catch (InterruptedException | IOException e) {
			throw new RegistryException("Error running command " + command, e);
		}
	}

	private static void checkResultStatus(int status) throws IOException {
		if (status != 0) {
			throw new IOException("Error on registry operation, status code is " + status);
		}
	}

	static void importRegistryTree(File sourceFile) {
		List<String> command = createRegeditImportCommand(sourceFile);
		executeCommand(command);
	}

	private static List<String> createRegeditImportCommand(File sourceFile) {
		List<String> command = new ArrayList<>();
		command.add("regedit");
		command.add("/S");
		command.add(sourceFile.getAbsolutePath());
		return command;
	}
}
