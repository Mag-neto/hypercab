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
