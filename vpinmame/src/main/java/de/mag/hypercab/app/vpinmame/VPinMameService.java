package de.mag.hypercab.app.vpinmame;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

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

	public Set<Rom> getInstalledRoms() {
		Set<Rom> roms = new TreeSet<>();
		List<String> romList = fileSystemService.getRomList();

		for (String romName : romList) {
			SectionVO romSettings = registryService.getRomSettings(romName);
			Rom rom = new Rom().withName(romName);
			if (romSettings != null) {
				rom.setDmdX(romSettings.getConfig(Rom.DMD_X));
				rom.setDmdY(romSettings.getConfig(Rom.DMD_Y));
			}
			roms.add(rom);
		}
		return roms;
	}

	public void storeRomFile(InputStream inputStream, String originalFilename) throws IOException {
		fileSystemService.writeRomFile(inputStream, originalFilename);
	}

	public void deleteRom(String romName) {
		fileSystemService.deleteRom(romName);
	}

	public void updateRegistry() throws IOException {
		registryService.writeRegistry();
	}

}
