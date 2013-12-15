package de.mag.hypercab.app.media.zip;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ZipTest {

	@Test
	public void unzip() {
		File archive = new File("src/test/resources/afm_113.zip");
		File destination = new File("target/unzipped");
		Zip.unzip(archive, destination);
		File extractedFile = new File("target/unzipped/DSC08609.JPG");
		Assert.assertTrue(extractedFile.exists());
	}
}
