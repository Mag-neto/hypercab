package de.mag.hypercab.web;

import java.io.IOException;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import de.mag.hypercab.app.media.MediaService;
import de.mag.hypercab.app.media.MediaType;

@Controller
@RequestMapping("/media")
public class HyperpinMediaController {

	private static final String FILEUPLOAD_FILE_PARAM = "file";
	private static final String FUTUREPINBALL_WHEEL = "/futurepinball/{tableRef}/wheel";
	private static final String FUTUREPINBALL_TABLE = "/futurepinball/{tableRef}/table";
	private static final String FUTUREPINBALL_BACKGLASS = "/futurepinball/{tableRef}/backglass";
	private static final String VISUALPINBALL_WHEEL = "/visualpinball/{tableRef}/wheel";
	private static final String VISUALPINBALL_TABLE = "/visualpinball/{tableRef}/table";
	private static final String VISUALPINBALL_BACKGLASS = "/visualpinball/{tableRef}/backglass";

	@Resource
	private MediaService mediaService;

	@ResponseBody
	@RequestMapping(VISUALPINBALL_BACKGLASS)
	public byte[] getVPBackglassImage(@PathVariable String tableRef) {
		return mediaService.getImageData(tableRef, MediaType.VP_BACKGLASS_IMAGE);
	}

	@ResponseBody
	@RequestMapping(value = VISUALPINBALL_BACKGLASS, method = RequestMethod.POST)
	public void storeVPBackglassImage(@RequestParam(FILEUPLOAD_FILE_PARAM) MultipartFile file,
			@PathVariable String tableRef)
			throws IOException {
		mediaService.storeMediaFile(file.getInputStream(), tableRef, MediaType.VP_BACKGLASS_IMAGE);
	}

	@ResponseBody
	@RequestMapping(VISUALPINBALL_TABLE)
	public byte[] getVPTableImage(@PathVariable String tableRef) {
		return mediaService.getImageData(tableRef, MediaType.VP_TABLE_IMAGE);
	}

	@ResponseBody
	@RequestMapping(value = VISUALPINBALL_TABLE, method = RequestMethod.POST)
	public void storeVPTableImage(@RequestParam(FILEUPLOAD_FILE_PARAM) MultipartFile file, @PathVariable String tableRef)
			throws IOException {
		mediaService.storeMediaFile(file.getInputStream(), tableRef, MediaType.VP_TABLE_IMAGE);
	}

	@ResponseBody
	@RequestMapping(VISUALPINBALL_WHEEL)
	public byte[] getVPWheelImage(@PathVariable String tableRef) {
		return mediaService.getImageData(tableRef, MediaType.VP_WHEEL_IMAGE);
	}

	@ResponseBody
	@RequestMapping(value = VISUALPINBALL_WHEEL, method = RequestMethod.POST)
	public void storeVPWheelImage(@RequestParam(FILEUPLOAD_FILE_PARAM) MultipartFile file, @PathVariable String tableRef)
			throws IOException {
		mediaService.storeMediaFile(file.getInputStream(), tableRef, MediaType.VP_WHEEL_IMAGE);
	}

	@ResponseBody
	@RequestMapping(FUTUREPINBALL_BACKGLASS)
	public byte[] getFPBackglassImage(@PathVariable String tableRef) {
		return mediaService.getImageData(tableRef, MediaType.FP_BACKGLASS_IMAGE);
	}

	@ResponseBody
	@RequestMapping(value = FUTUREPINBALL_BACKGLASS, method = RequestMethod.POST)
	public void storeFPBackglassImage(@RequestParam(FILEUPLOAD_FILE_PARAM) MultipartFile file,
			@PathVariable String tableRef)
			throws IOException {
		mediaService.storeMediaFile(file.getInputStream(), tableRef, MediaType.FP_BACKGLASS_IMAGE);
	}

	@ResponseBody
	@RequestMapping(FUTUREPINBALL_TABLE)
	public byte[] getFPTableImage(@PathVariable String tableRef) {
		return mediaService.getImageData(tableRef, MediaType.FP_TABLE_IMAGE);
	}

	@ResponseBody
	@RequestMapping(value = FUTUREPINBALL_TABLE, method = RequestMethod.POST)
	public void storeFPTableImage(@RequestParam(FILEUPLOAD_FILE_PARAM) MultipartFile file, @PathVariable String tableRef)
			throws IOException {
		mediaService.storeMediaFile(file.getInputStream(), tableRef, MediaType.FP_TABLE_IMAGE);
	}

	@ResponseBody
	@RequestMapping(FUTUREPINBALL_WHEEL)
	public byte[] getFPWheelImage(@PathVariable String tableRef) {
		return mediaService.getImageData(tableRef, MediaType.FP_WHEEL_IMAGE);
	}

	@ResponseBody
	@RequestMapping(value = FUTUREPINBALL_WHEEL, method = RequestMethod.POST)
	public void storeFPWheelImage(@RequestParam(FILEUPLOAD_FILE_PARAM) MultipartFile file, @PathVariable String tableRef)
			throws IOException {
		mediaService.storeMediaFile(file.getInputStream(), tableRef, MediaType.FP_WHEEL_IMAGE);
	}
}
