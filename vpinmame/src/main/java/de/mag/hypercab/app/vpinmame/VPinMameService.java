package de.mag.hypercab.app.vpinmame;

import java.io.IOException;
import java.io.InputStream;
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

	public void storeRomFile(InputStream inputStream, String originalFilename) throws IOException {
		fileSystemService.writeRomFile(inputStream, originalFilename);

	}

	public void deleteRom(String romName) {
		fileSystemService.deleteRom(romName);
	}

}
