package de.mag.hypercab.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import de.mag.hypercab.api.HypercabModule;
import de.mag.hypercab.app.server.ServerRegistry;

@Controller
@RequestMapping("/server")
public class ServerController {

	@Resource
	private ServerRegistry moduleRegistry;

	@ResponseBody
	@RequestMapping("")
	public List<HypercabModule> serverInfo() {
		return moduleRegistry.getRegisteredModules();
	}
}
