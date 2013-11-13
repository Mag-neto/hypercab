package de.mag.hypercab.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.testng.annotations.Test;

import de.mag.hypercab.AbstractWebIntegrationTest;

public class SettingsControllerTest extends AbstractWebIntegrationTest {

	@Test
	public void deliversSettings() throws Exception {
		mvc.perform(
				get("/settings"))
				.andExpect(status().isOk());
	}

	@Test
	public void respondsWithNotFoundOnInvalidGetRequest() throws Exception {
		mvc.perform(
				get("/settings/123"))
				.andExpect(status().isNotFound());
	}

	@Test
	public void updatesSettings() throws Exception {
		try (InputStream in = new FileInputStream("src/test/resources/settings.json")) {
			mvc.perform(
					put("/settings")
							.contentType(MediaType.APPLICATION_JSON)
							.content(new String(IOUtils.toByteArray(in))))
					.andExpect(status().isOk());
		}
	}

	@Test
	public void respondsWithBadRequestOnWrongUpdateDataFormat() throws Exception {
		try (InputStream in = new FileInputStream("src/test/resources/table.json")) {
			mvc.perform(
					put("/settings")
							.contentType(MediaType.APPLICATION_JSON)
							.content(new String(IOUtils.toByteArray(in))))
					.andExpect(status().isBadRequest());
		}
	}

}
