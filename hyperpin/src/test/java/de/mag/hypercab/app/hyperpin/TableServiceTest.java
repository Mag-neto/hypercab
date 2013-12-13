package de.mag.hypercab.app.hyperpin;

import org.testng.Assert;
import org.testng.annotations.Test;

import de.mag.hypercab.api.Table;

public class TableServiceTest {

	@Test
	public void convertsXmlStringToTableObject() {
		TableService tableService = new TableService();
		String xmlString = "<game name=\"fileName\"><description>description</description><manufacturer>manufacturer</manufacturer><year>year</year><type>machineType</type></game>";

		Table table = tableService.toTable(xmlString);

		Assert.assertNotNull(table);
		Assert.assertEquals(table.getFileName(), "fileName");
		Assert.assertEquals(table.getDescription(), "description");
		Assert.assertEquals(table.getManufacturer(), "manufacturer");
		Assert.assertEquals(table.getYear(), "year");
		Assert.assertEquals(table.getMachineType(), "machineType");
	}
}
