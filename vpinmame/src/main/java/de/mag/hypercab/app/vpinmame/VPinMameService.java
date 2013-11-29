package de.mag.hypercab.app.vpinmame;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import de.mag.hypercab.app.vpinmame.filesystem.FileSystemService;

@Service
public class VPinMameService {

	@Resource
	private FileSystemService fileSystemService;

	public List<String> getInstalledRoms() {
		return fileSystemService.getRomList();
	}

}
