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
				rom.setDmdCompact(romSettings.getConfig(Rom.DMD_COMPACT));
				rom.setDmdDoubleSize(romSettings.getConfig(Rom.DMD_DOUBLESIZE));
				rom.setDmdWidth(romSettings.getConfig(Rom.DMD_WIDTH));
				rom.setDmdHeight(romSettings.getConfig(Rom.DMD_HEIGHT));
				rom.setRotateLeft(romSettings.getConfig(Rom.DMD_ROTATION_LEFT));
				rom.setRotateRight(romSettings.getConfig(Rom.DMD_ROTATION_RIGHT));
			}
			roms.add(rom);
		}
		return roms;
	}

	public void updateRomSettings(Rom rom) throws IOException {
		SectionVO romSettings = registryService.getRomSettings(rom.getName());
		romSettings.setConfig(Rom.DMD_X, rom.getDmdX());
		romSettings.setConfig(Rom.DMD_Y, rom.getDmdY());
		romSettings.setConfig(Rom.DMD_COMPACT, rom.getDmdCompact());
		romSettings.setConfig(Rom.DMD_DOUBLESIZE, rom.getDmdDoubleSize());
		romSettings.setConfig(Rom.DMD_HEIGHT, rom.getDmdHeight());
		romSettings.setConfig(Rom.DMD_WIDTH, rom.getDmdWidth());
		romSettings.setConfig(Rom.DMD_ROTATION_LEFT, rom.getRotateLeft());
		romSettings.setConfig(Rom.DMD_ROTATION_RIGHT, rom.getRotateRight());
		registryService.updateRomSettings(romSettings);
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
