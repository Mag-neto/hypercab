package de.mag.hypercab.app.hyperpin.database;

import javax.annotation.Resource;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import de.mag.hypercab.AbstractIntegrationTest;
import de.mag.hypercab.api.Platform;
import de.mag.hypercab.api.Table;

public class DatabaseManagerTest extends AbstractIntegrationTest {

	@Resource
	private DatabaseManager databaseManager;
	private Table testTable;

	@BeforeClass
	public void init() {
		testTable = new Table();
		testTable.setActive(false);
		testTable.setDescription("Sorcerer (Williams 1985)");
		testTable.setFileName("Sorcerer_UR_VP9_v1.1.0_FS");
		testTable.setMachineType("SS");
		testTable.setManufacturer("Williams");
		testTable.setPlatform(Platform.VISUAL_PINBALL);
		testTable.setYear("1985");
	}

	@Test
	public void updatesTable() {
		Table dbTable = databaseManager.findTable("Sorcerer (Williams 1985)");
		Assert.assertEquals(dbTable.isActive(), true);
		databaseManager.updateTable("Sorcerer (Williams 1985)", testTable);
		dbTable = databaseManager.findTable("Sorcerer (Williams 1985)");
		Assert.assertEquals(dbTable.isActive(), false);
	}

}
