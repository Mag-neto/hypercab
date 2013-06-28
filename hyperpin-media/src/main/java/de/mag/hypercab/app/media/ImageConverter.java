package de.mag.hypercab.app.media;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;

@Component
public class ImageConverter {

	byte[] toByteArray(File imageFile) {
		try (InputStream inStream = new FileInputStream(imageFile)) {
			return IOUtils.toByteArray(inStream);
		} catch (IOException e) {
			// TODO: handle exception
			System.out.println("Error loading byte data from " + imageFile.getAbsolutePath());
			e.printStackTrace();
			return new byte[] {};
		}
	}

}
