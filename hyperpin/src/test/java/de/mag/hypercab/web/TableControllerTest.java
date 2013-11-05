package de.mag.hypercab.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.testng.annotations.Test;

import de.mag.hypercab.AbstractWebIntegrationTest;

public class TableControllerTest extends AbstractWebIntegrationTest {

	@Test
	public void deliversTables() throws Exception {
		mvc.perform(
				get("/tables"))
				.andExpect(status().isOk());
	}

	@Test
	public void savesTables() throws Exception {
		mvc.perform(
				get("/tables/save"))
				.andExpect(status().isOk());
	}

	@Test
	public void createsTable() throws Exception {
		try (InputStream in = new FileInputStream("src/test/resources/table.json")) {
			mvc.perform(
					post("/tables")
							.contentType(MediaType.APPLICATION_JSON)
							.content(new String(IOUtils.toByteArray(in))))
					.andExpect(status().isCreated());
		}
	}

	@Test
	public void updatesTable() throws Exception {
		try (InputStream in = new FileInputStream("src/test/resources/modifytable.json")) {
			mvc.perform(
					put("/tables/Sorcerer (Williams 1985)")
							.contentType(MediaType.APPLICATION_JSON)
							.content(new String(IOUtils.toByteArray(in))))
					.andExpect(status().isOk());
		}
	}

	@Test
	public void respondsWithBadRequestOnInvalidData() throws Exception {
		try (InputStream in = new FileInputStream("src/test/resources/settings.json")) {
			mvc.perform(
					put("/tables/Sorcerer (Williams 1985)")
							.contentType(MediaType.APPLICATION_JSON)
							.content(new String(IOUtils.toByteArray(in))))
					.andExpect(status().isBadRequest());
		}
	}
}
