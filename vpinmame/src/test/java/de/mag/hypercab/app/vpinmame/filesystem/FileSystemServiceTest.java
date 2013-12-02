package de.mag.hypercab.app.vpinmame.filesystem;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.annotation.Resource;

import org.testng.Assert;
import org.testng.annotations.Test;

import de.mag.hypercab.AbstractIntegrationTest;

public class FileSystemServiceTest extends AbstractIntegrationTest {

	@Resource
	private FileSystemService fileSystemService;

	@Test
	public void listsRomfiles() {
		List<String> romList = fileSystemService.getRomList();
		Assert.assertNotNull(romList);
		Assert.assertTrue(romList.size() > 0);
	}

	@Test
	public void storesRomFile() throws IOException {
		InputStream in = new FileInputStream("target/test-classes/VPinMame/roms/afm_113.zip");
		fileSystemService.writeRomFile(in, "testrom");
		List<String> romList = fileSystemService.getRomList();
		Assert.assertTrue(romList.contains("testrom"));
	}

	@Test
	public void deletesFoldersAndZips() {
		fileSystemService.deleteRom("afm_113b");
		fileSystemService.deleteRom("bk_l4");
		List<String> romList = fileSystemService.getRomList();
		Assert.assertFalse(romList.contains("afm_113b"));
		Assert.assertFalse(romList.contains("bk_l4.zip"));
	}

}
