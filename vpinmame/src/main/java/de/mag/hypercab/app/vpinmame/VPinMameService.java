package de.mag.hypercab.app.vpinmame;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import de.mag.hypercab.api.ini.SectionVO;
import de.mag.hypercab.app.vpinmame.filesystem.FileSystemService;
import de.mag.hypercab.app.vpinmame.registry.RegistryService;

@Service
public class VPinMameService {

	@Resource
	private FileSystemService fileSystemService;

	@Resource
	private RegistryService registryService;

	public Map<String, SectionVO> getInstalledRoms() {
		Map<String, SectionVO> roms = new HashMap<>();
		List<String> romList = fileSystemService.getRomList();
		for (String romName : romList) {
			roms.put(romName, registryService.getRomSettings(romName));
		}
		return roms;
	}

	public void storeRomFile(InputStream inputStream, String originalFilename) throws IOException {
		fileSystemService.writeRomFile(inputStream, originalFilename);
	}

	public void deleteRom(String romName) {
		fileSystemService.deleteRom(romName);
	}

}
