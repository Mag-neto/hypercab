package de.mag.hypercab.web;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import de.mag.hypercab.api.HypercabModule;
import de.mag.hypercab.app.server.ServerRegistry;
import de.mag.hypercab.app.server.settings.HyperpinSettings;

@Controller
@RequestMapping("/server")
public class ServerController {

	@Resource
	private ServerRegistry moduleRegistry;

	@Resource
	private HyperpinSettings hyperpinSettings;

	@ResponseBody
	@RequestMapping("")
	public List<HypercabModule> serverInfo() {
		return moduleRegistry.getRegisteredModules();
	}

	@ResponseBody
	@RequestMapping("/config")
	public Map<String, Map<String, String>> getHyperpinConfig() {
		return hyperpinSettings.getSettings();

	}

	@ResponseBody
	@RequestMapping(value = "/config", method = RequestMethod.PUT, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public void storeConfig(@RequestBody Map<String, Map<String, String>> config) throws IOException {
		hyperpinSettings.saveSettings(config);
	}

}
