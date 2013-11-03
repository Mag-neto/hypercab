package de.mag.hypercab.web;

import java.util.Set;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import de.mag.hypercab.api.Table;
import de.mag.hypercab.app.hyperpin.TableService;

@Controller
@RequestMapping("/tables")
public class TableController {

	@Resource
	private TableService tableService;

	@ResponseBody
	@RequestMapping(method = RequestMethod.GET)
	public Set<Table> getInstalledTables() {
		return tableService.getInstalledTables();
	}

	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public void createTable(@RequestBody Table table) {
		tableService.addTable(table);
	}

	@ResponseBody
	@RequestMapping(value = "/{description}", method = RequestMethod.PUT, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public void saveTable(@PathVariable String description, @RequestBody Table table) {
		tableService.updateTable(description, table);
	}

	@ResponseBody
	@RequestMapping("/save")
	public void saveTables() {
		tableService.save();
	}

}
