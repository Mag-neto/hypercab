package de.mag.hypercab.web;

import java.util.Set;

import javax.annotation.Resource;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import de.mag.hypercab.api.Table;
import de.mag.hypercab.app.hyperpin.database.TableService;

@Controller
@RequestMapping("/tables")
public class TableController {

	private static final String DEACTIVATE_TABLE = "/{tableDesc}/deactivate";
	private static final String ACTIVATE_TABLE = "/{tableDesc}/activate";
	private static final String SAVE_TABLES = "/save";
	private static final String FUTUREPINBALL_TABLES = "/futurepinball";
	private static final String VISUALPINBALL_TABLES = "/visualpinball";
	private static final String INACTIVE_TABLES = "/inactive";
	private static final String ACTIVE_TABLES = "/active";
	@Resource
	private TableService tableService;

	@ResponseBody
	@RequestMapping(value = "", method = RequestMethod.GET)
	public Set<Table> getInstalledTables() {
		return tableService.getInstalledTables();
	}

	@ResponseBody
	@RequestMapping(value = "", method = RequestMethod.PUT, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public void createTable(@RequestBody Table table) {
		tableService.addTable(table);
	}

	@ResponseBody
	@RequestMapping(value = ACTIVE_TABLES, method = RequestMethod.GET)
	public Set<Table> getActiveTables() {
		return tableService.getActiveTables();
	}

	@ResponseBody
	@RequestMapping(value = INACTIVE_TABLES, method = RequestMethod.GET)
	public Set<Table> getInactiveTables() {
		return tableService.getInactiveTables();
	}

	@ResponseBody
	@RequestMapping(value = VISUALPINBALL_TABLES, method = RequestMethod.GET)
	public Set<Table> getVPTables() {
		return tableService.getVPTables();
	}

	@ResponseBody
	@RequestMapping(value = FUTUREPINBALL_TABLES, method = RequestMethod.GET)
	public Set<Table> getFPTables() {
		return tableService.getFPTables();
	}

	@ResponseBody
	@RequestMapping(SAVE_TABLES)
	public void saveTables() {
		tableService.save();
	}

	@ResponseBody
	@RequestMapping(value = ACTIVATE_TABLE)
	public void activateTable(@PathVariable String tableDesc) {
		tableService.activateTable(tableDesc);
	}

	@ResponseBody
	@RequestMapping(value = DEACTIVATE_TABLE)
	public void deactivateTable(@PathVariable String tableDesc) {
		tableService.deactivateTable(tableDesc);
	}

}
