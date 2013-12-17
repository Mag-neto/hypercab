package de.mag.hypercab.app.hyperpin.database;

import java.io.File;
import java.util.Collection;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.mag.hypercab.api.Platform;
import de.mag.hypercab.api.Table;

public class DatabaseHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseHandler.class);

	private final Platform platform;
	private final File databaseFile;
	private final boolean handleActiveTables;
	private Map<String, Table> tables;

	DatabaseHandler(Platform platform, File dbFile, boolean handleActiveTables) {
		this.platform = platform;
		this.databaseFile = dbFile;
		this.handleActiveTables = handleActiveTables;
		initialize();
	}

	private void initialize() {
		tables = XmlDatabase.readFromFile(databaseFile);
		for (Table t : tables.values()) {
			t.setPlatform(platform);
			t.setActive(handleActiveTables);
		}
		LOGGER.debug("Initialized DatabaseHandler for platform {} and file {}"
				, platform.name(), databaseFile.getName());
	}

	Collection<Table> getTables() {
		return this.tables.values();
	}

	void saveDatabase() {
		XmlDatabase.storeToFile(databaseFile, getTables());
		LOGGER.debug("Wrote database file {}", databaseFile.getName());
	}

	synchronized void addTable(Table table) {
		this.tables.put(table.getDescription(), table);
	}

	synchronized void removeTable(Table table) {
		this.tables.remove(table.getDescription());
	}

	Platform getPlatform() {
		return this.platform;
	}

	boolean handlesActiveTables() {
		return this.handleActiveTables;
	}

}
