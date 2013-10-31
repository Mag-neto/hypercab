package de.mag.hypercab.app.hyperpin.database;

import java.io.File;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import de.mag.hypercab.api.Platform;
import de.mag.hypercab.api.Table;
import de.mag.hypercab.app.hyperpin.Configuration;

@Component
public class DatabaseManager {

	private static final String VP_DB_ACTIVE = "/Databases/Visual Pinball/Visual Pinball.xml";
	private static final String VP_DB_INACTIVE = "/Databases/Visual Pinball/Visual Pinball Inactive.xml";
	private static final String FP_DB_ACTIVE = "/Databases/Future Pinball/Future Pinball.xml";
	private static final String FP_DB_INACTIVE = "/Databases/Future Pinball/Future Pinball Inactive.xml";

	@Resource
	private Configuration configuration;

	private Map<String, Table> vpActiveTables = null;
	private Map<String, Table> vpInactiveTables = null;
	private Map<String, Table> fpActiveTables = null;
	private Map<String, Table> fpInactiveTables = null;
	private File hyperpinRootPath;

	@PostConstruct
	public void init() {
		this.hyperpinRootPath = configuration.getHyperpinPath();
		this.vpActiveTables = XmlDatabase.readDb(new File(hyperpinRootPath, VP_DB_ACTIVE));
		addPlatformAndStatus(vpActiveTables, Platform.VISUAL_PINBALL, true);
		this.vpInactiveTables = XmlDatabase.readDb(new File(hyperpinRootPath, VP_DB_INACTIVE));
		addPlatformAndStatus(vpInactiveTables, Platform.VISUAL_PINBALL, false);
		this.fpActiveTables = XmlDatabase.readDb(new File(hyperpinRootPath, FP_DB_ACTIVE));
		addPlatformAndStatus(fpActiveTables, Platform.FUTURE_PINBALL, true);
		this.fpInactiveTables = XmlDatabase.readDb(new File(hyperpinRootPath, FP_DB_INACTIVE));
		addPlatformAndStatus(fpInactiveTables, Platform.FUTURE_PINBALL, false);
	}

	private void addPlatformAndStatus(Map<String, Table> tables, Platform platform, boolean isActive) {
		for (Table t : tables.values()) {
			t.setPlatform(platform);
			t.setActive(isActive);
		}
	}

	Set<Table> getInstalledTables() {
		Set<Table> tables = new HashSet<>();
		tables.addAll(vpActiveTables.values());
		tables.addAll(vpInactiveTables.values());
		tables.addAll(fpActiveTables.values());
		tables.addAll(fpInactiveTables.values());
		return tables;
	}

	void save() {
		XmlDatabase.writeDb(new File(hyperpinRootPath, VP_DB_ACTIVE), vpActiveTables.values());
		XmlDatabase.writeDb(new File(hyperpinRootPath, VP_DB_INACTIVE), vpInactiveTables.values());
		XmlDatabase.writeDb(new File(hyperpinRootPath, FP_DB_ACTIVE), fpActiveTables.values());
		XmlDatabase.writeDb(new File(hyperpinRootPath, FP_DB_INACTIVE), fpInactiveTables.values());
	}

	void addTable(Table table) {
		switch (table.getPlatform()) {
		case VISUAL_PINBALL:
			if (table.isActive()) {
				vpActiveTables.put(table.getDescription(), table);
			} else {
				vpInactiveTables.put(table.getDescription(), table);
			}
			break;
		case FUTURE_PINBALL:
			if (table.isActive()) {
				fpActiveTables.put(table.getDescription(), table);
			} else {
				fpInactiveTables.put(table.getDescription(), table);
			}
			break;
		}
	}

	void removeTable(Table table) {
		switch (table.getPlatform()) {
		case VISUAL_PINBALL:
			if (table.isActive()) {
				vpActiveTables.remove(table.getDescription());
			} else {
				vpInactiveTables.remove(table.getDescription());
			}
			break;
		case FUTURE_PINBALL:
			if (table.isActive()) {
				fpActiveTables.remove(table.getDescription());
			} else {
				fpInactiveTables.remove(table.getDescription());
			}
			break;
		}
	}

	private void toggleActive(Table table) {
		switch (table.getPlatform()) {
		case VISUAL_PINBALL:
			if (!table.isActive()) {
				synchronized (this) {
					table.setActive(true);
					vpInactiveTables.remove(table.getDescription());
					vpActiveTables.put(table.getDescription(), table);
				}
			} else {
				synchronized (this) {
					table.setActive(false);
					vpActiveTables.remove(table.getDescription());
					vpInactiveTables.put(table.getDescription(), table);
				}
			}
			break;
		case FUTURE_PINBALL:
			if (!table.isActive()) {
				synchronized (this) {
					table.setActive(true);
					fpInactiveTables.remove(table.getDescription());
					fpActiveTables.put(table.getDescription(), table);
				}
			} else {
				synchronized (this) {
					table.setActive(false);
					fpActiveTables.remove(table.getDescription());
					fpInactiveTables.put(table.getDescription(), table);
				}
			}
			break;
		}
	}

	void updateTable(String description, Table table) {
		Table tableToUpdate = findTable(description);

		if (tableToUpdate.isActive() != table.isActive()) {
			toggleActive(tableToUpdate);
		}
		tableToUpdate.setActive(table.isActive());
		tableToUpdate.setDescription(table.getDescription());
		tableToUpdate.setFileName(table.getFileName());
		tableToUpdate.setMachineType(table.getMachineType());
		tableToUpdate.setManufacturer(table.getManufacturer());
		tableToUpdate.setPlatform(table.getPlatform());
		tableToUpdate.setYear(table.getYear());
	}

	Table findTable(String description) {
		Set<Table> installedTables = getInstalledTables();
		for (Table currentTable : installedTables) {
			if (currentTable.getDescription().equals(description)) {
				return currentTable;
			}
		}
		throw new DatabaseException("No table with description " + description
				+ " registered in database.");
	}
}
