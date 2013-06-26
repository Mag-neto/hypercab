package de.mag.hypercab.app.tables;

import java.util.Collection;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import de.mag.hypercab.api.Table;
import de.mag.hypercab.api.TableEnhancer;
import de.mag.hypercab.app.database.DatabaseManager;
import de.mag.hypercab.app.server.ServerRegistry;

@Service
public class TableService {

	@Resource
	private ServerRegistry serverRegistry;

	@Resource
	private DatabaseManager databaseManager;

	public Set<Table> getInstalledTables() {
		Set<Table> installedTables = databaseManager.getInstalledTables();
		enhanceTables(installedTables);
		return installedTables;
	}

	public Set<Table> getActiveTables() {
		Set<Table> activeTables = databaseManager.getActiveTables();
		enhanceTables(activeTables);
		return activeTables;
	}

	public Set<Table> getInactiveTables() {
		Set<Table> inactiveTables = databaseManager.getInactiveTables();
		enhanceTables(inactiveTables);
		return inactiveTables;
	}

	public Set<Table> getVPTables() {
		Set<Table> vpTables = databaseManager.getVPTables();
		enhanceTables(vpTables);
		return vpTables;
	}

	public Set<Table> getFPTables() {
		Set<Table> fpTables = databaseManager.getFPTables();
		enhanceTables(fpTables);
		return fpTables;
	}

	public void activateTable(String tableRef) {
		databaseManager.activateTable(tableRef);
	}

	public void deactivateTable(String tableRef) {
		databaseManager.deactivateTable(tableRef);
	}

	public void save() {
		databaseManager.save();
	}

	private void enhanceTables(Collection<Table> tables) {
		for (TableEnhancer enhancer : serverRegistry.getTableEnhancers()) {
			enhancer.enhance(tables);
		}
	}

}
