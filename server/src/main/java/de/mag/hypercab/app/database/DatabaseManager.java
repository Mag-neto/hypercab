package de.mag.hypercab.app.database;

import java.io.File;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import de.mag.hypercab.api.Table;
import de.mag.hypercab.app.server.Configuration;

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
		this.vpInactiveTables = XmlDatabase.readDb(new File(hyperpinRootPath, VP_DB_INACTIVE));
		this.fpActiveTables = XmlDatabase.readDb(new File(hyperpinRootPath, FP_DB_ACTIVE));
		this.fpInactiveTables = XmlDatabase.readDb(new File(hyperpinRootPath, FP_DB_INACTIVE));
	}

	public void activateTable(String tableDescription) {
		if (vpInactiveTables.containsKey(tableDescription)) {
			switchActive(vpInactiveTables.get(tableDescription));
		} else if (fpInactiveTables.containsKey(tableDescription)) {
			switchActive(fpInactiveTables.get(tableDescription));
		}
	}

	public void deactivateTable(String tableDescription) {
		if (vpActiveTables.containsKey(tableDescription)) {
			switchInactive(vpActiveTables.get(tableDescription));
		} else if (fpActiveTables.containsKey(tableDescription)) {
			switchInactive(fpActiveTables.get(tableDescription));
		}
	}

	private void switchActive(Table table) {
		switch (table.getPlatform()) {
		case VISUAL_PINBALL:
			synchronized (this) {
				table.setActive(true);
				vpInactiveTables.remove(table.getDescription());
				vpActiveTables.put(table.getDescription(), table);
			}
			break;
		case FUTURE_PINBALL:
			synchronized (this) {
				table.setActive(true);
				fpInactiveTables.remove(table.getDescription());
				fpActiveTables.put(table.getDescription(), table);
			}
			break;
		}
	}

	private void switchInactive(Table table) {
		switch (table.getPlatform()) {
		case VISUAL_PINBALL:
			synchronized (this) {
				table.setActive(false);
				vpActiveTables.remove(table.getDescription());
				vpInactiveTables.put(table.getDescription(), table);
			}
			break;
		case FUTURE_PINBALL:
			synchronized (this) {
				table.setActive(false);
				fpActiveTables.remove(table.getDescription());
				fpInactiveTables.put(table.getDescription(), table);
			}
			break;
		}
	}
}
