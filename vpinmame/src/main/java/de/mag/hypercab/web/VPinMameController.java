package de.mag.hypercab.web;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;

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

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/roms/{romName}", method = RequestMethod.DELETE)
	public void deleteRom(@PathVariable String romName) {
		vPinMameService.deleteRom(romName);
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/roms", method = RequestMethod.POST)
	public void storeVPTableImage(@RequestParam("file") MultipartFile file) throws IOException {
		vPinMameService.storeRomFile(file.getInputStream(), file.getOriginalFilename());
	}

}
