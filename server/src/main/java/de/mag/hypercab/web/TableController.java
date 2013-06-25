package de.mag.hypercab.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import de.mag.hypercab.api.Table;
import de.mag.hypercab.app.database.DatabaseManager;

@Controller
@RequestMapping("/tables")
public class TableController {

	@Resource
	private DatabaseManager databaseManager;

	@ResponseBody
	@RequestMapping(value = "", method = RequestMethod.GET)
	public List<Table> getInstalledTables() {
		return databaseManager.getInstalledTables();
	}

	@ResponseBody
	@RequestMapping(value = "/active", method = RequestMethod.GET)
	public List<Table> getActiveTables() {
		return databaseManager.getActiveTables();
	}

	@ResponseBody
	@RequestMapping(value = "/inactive", method = RequestMethod.GET)
	public List<Table> getInactiveTables() {
		return databaseManager.getInactiveTables();
	}

}
