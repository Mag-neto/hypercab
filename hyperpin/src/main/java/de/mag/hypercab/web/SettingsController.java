package de.mag.hypercab.web;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import de.mag.hypercab.api.ini.SectionVO;
import de.mag.hypercab.app.hyperpin.config.Configuration;

@Controller
@RequestMapping("/settings")
public class SettingsController {

	@Resource
	private Configuration configuration;

	@ResponseBody
	@RequestMapping("")
	public List<SectionVO> getHyperpinConfig() {
		return configuration.getSettings();

	}

	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "", method = RequestMethod.PUT, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public void storeConfig(@RequestBody SectionVO[] sections) throws IOException {
		configuration.saveSettings(Arrays.asList(sections));
	}

}
