package de.mag.hypercab.app.vpinmame.filesystem;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import de.mag.hypercab.app.hyperpin.config.Configuration;

@Service
public class FileSystemService {

	private static final Logger LOGGER = LoggerFactory.getLogger(FileSystemService.class);

	private static String ROMS_SUBPATH = "roms";

	@Resource
	private Configuration configuration;

	@Resource
	private RomFilenameFilter romFilenameFilter;

	private File vpinmameRomPath;

	@PostConstruct
	void init() {
		this.vpinmameRomPath = new File(configuration.getVpinmamePath(), ROMS_SUBPATH);
	}

	public List<String> getRomList() {
		String[] roms = vpinmameRomPath.list(romFilenameFilter);
		if (roms == null) {
			roms = new String[0];
		}
		roms = stripFileEndings(roms);
		LOGGER.debug("Returning {} roms", roms.length);
		return Arrays.asList(roms);
	}

	private String[] stripFileEndings(String[] roms) {
		for (int i = 0; i < roms.length; i++) {
			roms[i] = FilenameUtils.getBaseName(roms[i]);
		}
		return roms;
	}

	public void writeRomFile(InputStream fileStream, String romName) throws IOException {
		File targetFile = new File(vpinmameRomPath, romName);
		try (OutputStream out = new FileOutputStream(targetFile)) {
			IOUtils.copyLarge(fileStream, out);
			LOGGER.debug("Stored rom file {} to {}.", romName, targetFile.getAbsolutePath());
		} finally {
			IOUtils.closeQuietly(fileStream);
		}
	}

	public void deleteRom(String romName) {
		File fileToDelete = new File(vpinmameRomPath, romName + ".zip");
		if (!fileToDelete.exists()) {
			fileToDelete = new File(vpinmameRomPath, romName);
		}
		FileUtils.deleteQuietly(fileToDelete);
		LOGGER.debug("Deleted rom file {}.", fileToDelete.getAbsolutePath());
	}
}
