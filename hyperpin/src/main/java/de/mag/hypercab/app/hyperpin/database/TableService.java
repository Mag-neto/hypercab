package de.mag.hypercab.app.hyperpin.database;

import java.util.Collection;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import de.mag.hypercab.api.Table;
import de.mag.hypercab.api.TableEnhancer;
import de.mag.hypercab.app.ServerRegistry;

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

	public void activateTable(String tableRef) {
		databaseManager.activateTable(tableRef);
	}

	public void deactivateTable(String tableRef) {
		databaseManager.deactivateTable(tableRef);
	}

	public void save() {
		databaseManager.save();
	}

	public void addTable(Table table) {
		databaseManager.addTable(table);
	}

	public void removeTable(Table table) {
		databaseManager.removeTable(table);
	}

	private void enhanceTables(Collection<Table> tables) {
		for (TableEnhancer enhancer : serverRegistry.getTableEnhancers()) {
			enhancer.enhance(tables);
		}
	}

}
