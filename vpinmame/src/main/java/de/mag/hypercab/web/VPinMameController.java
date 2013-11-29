package de.mag.hypercab.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import de.mag.hypercab.app.vpinmame.VPinMameService;

@Controller
@RequestMapping("/vpinmame")
public class VPinMameController {

	@Resource
	private VPinMameService vPinMameService;

	@ResponseBody
	@RequestMapping(value = "/roms", method = RequestMethod.GET)
	public List<String> getRomList() {
		return vPinMameService.getInstalledRoms();
	}

}
