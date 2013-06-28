package de.mag.hypercab.web;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import de.mag.hypercab.app.media.MediaService;
import de.mag.hypercab.app.media.MediaType;

@Controller
@RequestMapping("/media")
public class HyperpinMediaController {

	@Resource
	private MediaService mediaService;

	@ResponseBody
	@RequestMapping("/visualpinball/{tableRef}/backglass")
	public byte[] getVPBackglassImage(@PathVariable String tableRef) {
		return mediaService.getImageData(tableRef, MediaType.VP_BACKGLASS_IMAGE);
	}

	@ResponseBody
	@RequestMapping("/visualpinball/{tableRef}/table")
	public byte[] getVPTableImage(@PathVariable String tableRef) {
		return mediaService.getImageData(tableRef, MediaType.VP_TABLE_IMAGE);
	}

	@ResponseBody
	@RequestMapping("/visualpinball/{tableRef}/wheel")
	public byte[] getVPWheelImage(@PathVariable String tableRef) {
		return mediaService.getImageData(tableRef, MediaType.VP_WHEEL_IMAGE);
	}

	@ResponseBody
	@RequestMapping("/futurepinball/{tableRef}/backglass")
	public byte[] getFPBackglassImage(@PathVariable String tableRef) {
		return mediaService.getImageData(tableRef, MediaType.FP_BACKGLASS_IMAGE);
	}

	@ResponseBody
	@RequestMapping("/futurepinball/{tableRef}/table")
	public byte[] getFPTableImage(@PathVariable String tableRef) {
		return mediaService.getImageData(tableRef, MediaType.FP_TABLE_IMAGE);
	}

	@ResponseBody
	@RequestMapping("/futurepinball/{tableRef}/wheel")
	public byte[] getFPWheelImage(@PathVariable String tableRef) {
		return mediaService.getImageData(tableRef, MediaType.FP_WHEEL_IMAGE);
	}

}
