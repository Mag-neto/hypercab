package de.mag.hypercab.app.media;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ImageConverterTest {

	private ImageConverter imageConverter;

	@BeforeClass
	public void setup() {
		this.imageConverter = new ImageConverter();
	}

	@Test
	public void imagesAreLoaded() {
		byte[] imgData = imageConverter.toByteArray(new File("src/test/resources/AFM.png"));
		Assert.assertNotNull(imgData);
		Assert.assertTrue(imgData.length == 3793235);
	}
}
