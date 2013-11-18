package de.mag.hypercab.app.hyperpin.database;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import de.mag.hypercab.api.Platform;
import de.mag.hypercab.api.Table;
import de.mag.hypercab.app.hyperpin.config.Configuration;

@Component
public class DatabaseManager {

	private static final String VP_DB_ACTIVE = "/Databases/Visual Pinball/Visual Pinball.xml";
	private static final String VP_DB_INACTIVE = "/Databases/Visual Pinball/Visual Pinball Inactive.xml";
	private static final String FP_DB_ACTIVE = "/Databases/Future Pinball/Future Pinball.xml";
	private static final String FP_DB_INACTIVE = "/Databases/Future Pinball/Future Pinball Inactive.xml";

	@Resource
	private Configuration configuration;
	private File hyperpinRootPath;
	private Set<DatabaseHandler> databaseHandlers;

	@PostConstruct
	public void init() {
		this.hyperpinRootPath = configuration.getHyperpinPath();
		initDatabaseHandlers();
	}

	private void initDatabaseHandlers() {
		databaseHandlers = new HashSet<>();
		databaseHandlers
				.add(
				new DatabaseHandler(Platform.VISUAL_PINBALL, new File(hyperpinRootPath, VP_DB_ACTIVE), true));
		databaseHandlers.add(
				new DatabaseHandler(Platform.VISUAL_PINBALL, new File(hyperpinRootPath, VP_DB_INACTIVE),
						false));
		databaseHandlers
				.add(
				new DatabaseHandler(Platform.FUTURE_PINBALL, new File(hyperpinRootPath, FP_DB_ACTIVE), true));
		databaseHandlers.add(
				new DatabaseHandler(Platform.FUTURE_PINBALL, new File(hyperpinRootPath, FP_DB_INACTIVE),
						false));
	}

	public Set<Table> getInstalledTables() {
		Set<Table> tables = new HashSet<>();
		for (DatabaseHandler dbHandler : databaseHandlers) {
			tables.addAll(dbHandler.getTables());
		}
		return tables;
	}

	public void save() {
		for (DatabaseHandler dbHandler : databaseHandlers) {
			dbHandler.saveDatabase();
		}
	}

	public void addTable(Table table) {
		DatabaseHandler databaseHandler = findMatchingHandler(table);
		databaseHandler.addTable(table);
	}

	private DatabaseHandler findMatchingHandler(Table table) {
		DatabaseHandler handler = null;
		for (DatabaseHandler dbHandler : databaseHandlers) {
			if (dbHandler.getPlatform().equals(table.getPlatform())) {
				if (dbHandler.handlesActiveTables() == table.isActive()) {
					handler = dbHandler;
				}
			}
		}
		if (handler == null) {
			throw new DatabaseException("No matching DatabaseHandler for table " + table);
		}
		return handler;
	}

	public void removeTable(Table table) {
		DatabaseHandler databaseHandler = findMatchingHandler(table);
		databaseHandler.removeTable(table);
	}

	public void updateTable(String description, Table table) {
		Table tableToUpdate = findTable(description);
		DatabaseHandler databaseHandler = findMatchingHandler(tableToUpdate);
		databaseHandler.removeTable(tableToUpdate);
		databaseHandler = findMatchingHandler(table);
		databaseHandler.addTable(table);
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
