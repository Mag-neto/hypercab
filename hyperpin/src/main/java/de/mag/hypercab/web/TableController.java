package de.mag.hypercab.web;

import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import de.mag.hypercab.api.Table;
import de.mag.hypercab.app.hyperpin.database.TableService;

@Controller
@RequestMapping("/tables")
public class TableController {

	@Resource
	private TableService tableService;

	@ResponseBody
	@RequestMapping(value = "", method = RequestMethod.GET)
	public Set<Table> getInstalledTables() {
		return tableService.getInstalledTables();
	}

	@ResponseBody
	@RequestMapping(value = "/active", method = RequestMethod.GET)
	public Set<Table> getActiveTables() {
		return tableService.getActiveTables();
	}

	@ResponseBody
	@RequestMapping(value = "/inactive", method = RequestMethod.GET)
	public Set<Table> getInactiveTables() {
		return tableService.getInactiveTables();
	}

	@ResponseBody
	@RequestMapping(value = "/visualpinball", method = RequestMethod.GET)
	public Set<Table> getVPTables() {
		return tableService.getVPTables();
	}

	@ResponseBody
	@RequestMapping(value = "/futurepinball", method = RequestMethod.GET)
	public Set<Table> getFPTables() {
		return tableService.getFPTables();
	}

	@ResponseBody
	@RequestMapping("/save")
	public void saveTables() {
		tableService.save();
	}

	@ResponseBody
	@RequestMapping(value = "/{tableDesc}/activate")
	public void activateTable(@PathVariable String tableDesc) {
		tableService.activateTable(tableDesc);
	}

	@ResponseBody
	@RequestMapping(value = "/{tableDesc}/deactivate")
	public void deactivateTable(@PathVariable String tableDesc) {
		tableService.deactivateTable(tableDesc);
	}

}
