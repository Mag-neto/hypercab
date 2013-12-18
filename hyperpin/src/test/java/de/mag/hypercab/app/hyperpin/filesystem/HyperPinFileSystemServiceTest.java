package de.mag.hypercab.app.hyperpin.filesystem;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.annotation.Resource;

import org.testng.Assert;
import org.testng.annotations.Test;

import de.mag.hypercab.AbstractIntegrationTest;

public class HyperPinFileSystemServiceTest extends AbstractIntegrationTest {

	@Resource
	private HyperPinFileSystemService hyperPinFileSystemService;

	@Test
	public void listsFilesWithoutDirectories() {
		List<String> files = hyperPinFileSystemService.getFiles();
		Assert.assertFalse(files.contains("Databases"));
		Assert.assertFalse(files.contains("Settings"));
		Assert.assertEquals(files.size(), 1);
	}

	@Test(dependsOnMethods = { "listsFilesWithoutDirectories" })
	public void createsNewFile() throws IOException {
		try (InputStream in = new FileInputStream("src/test/resources/table.json")) {
			hyperPinFileSystemService.addFile(in, "myNewFile.json");
			List<String> files = hyperPinFileSystemService.getFiles();
			Assert.assertTrue(files.contains("myNewFile.json"));
		}
	}

	@Test(dependsOnMethods = { "createsNewFile" })
	public void renamesFile() {
		List<String> files = hyperPinFileSystemService.getFiles();
		Assert.assertTrue(files.contains("myNewFile.json"));
		hyperPinFileSystemService.renameFile("myNewFile.json", "myRenamedFile.json");
		files = hyperPinFileSystemService.getFiles();
		Assert.assertFalse(files.contains("myNewFile.json"));
		Assert.assertTrue(files.contains("myRenamedFile.json"));
	}

	@Test(dependsOnMethods = { "renamesFile" })
	public void deletesFile() {
		List<String> files = hyperPinFileSystemService.getFiles();
		Assert.assertTrue(files.contains("myRenamedFile.json"));
		hyperPinFileSystemService.removeFile("myRenamedFile.json");
		files = hyperPinFileSystemService.getFiles();
		Assert.assertFalse(files.contains("myRenamedFile.json"));
	}
}
