package de.mag.hypercab.web;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;

import de.mag.hypercab.app.hyperpin.filesystem.HyperPinFileSystemService;

@Controller
@RequestMapping("/hyperpinfiles")
public class HyperPinFilesController {

	private static final Logger LOGGER = LoggerFactory.getLogger(HyperPinFilesController.class);

	@Resource
	private HyperPinFileSystemService hyperPinFileSystemService;

	@ResponseBody
	@RequestMapping(method = RequestMethod.GET)
	public List<String> getHyperPinFiles() {
		return hyperPinFileSystemService.getFiles();
	}

	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(method = RequestMethod.POST)
	public void createHyperPinFile(@RequestParam("file") MultipartFile file) throws IOException {
		hyperPinFileSystemService.addFile(file.getInputStream(), file.getOriginalFilename());
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/{fileName}.{ext}", method = RequestMethod.DELETE)
	public void deleteHyperPinFile(@PathVariable String fileName, @PathVariable String ext) {
		LOGGER.debug("Delete request with file name " + fileName + "." + ext);
		hyperPinFileSystemService.removeFile(fileName + "." + ext);
	}

}
