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
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

import de.mag.hypercab.app.hyperpin.config.Configuration;

@Service
public class FileSystemService {

	private static String ROMS_SUBPATH = "roms";

	@Resource
	private Configuration configuration;

	private File vpinmameRomPath;

	@PostConstruct
	void init() {
		this.vpinmameRomPath = new File(configuration.getVpinmamePath(), ROMS_SUBPATH);
	}

	public List<String> getRomList() {
		return Arrays.asList(vpinmameRomPath.list());
	}

	public void writeRomFile(InputStream fileStream, String romName) throws IOException {
		try (OutputStream out = new FileOutputStream(new File(vpinmameRomPath, romName + ".zip"))) {
			IOUtils.copyLarge(fileStream, out);
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
	}

}
