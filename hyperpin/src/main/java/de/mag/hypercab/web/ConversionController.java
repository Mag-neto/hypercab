package de.mag.hypercab.web;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import de.mag.hypercab.api.Table;
import de.mag.hypercab.app.hyperpin.TableService;

@Controller
@RequestMapping("/convert")
public class ConversionController {

	@Resource
	private TableService tableService;

	@ResponseBody
	@RequestMapping(value = "/table", method = RequestMethod.GET)
	public Table convertToTable(@RequestParam("xml") String xmlString) {
		return tableService.toTable(xmlString);
	}

}
