package de.mag.hypercab.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.FileInputStream;
import java.io.InputStream;

import javax.annotation.Resource;

import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import de.mag.hypercab.AbstractWebIntegrationTest;

public class HyperpinControllerTest extends AbstractWebIntegrationTest {

	@Resource
	WebApplicationContext wac;

	MockMvc mvc;

	@BeforeClass
	public void setup() {
		mvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void serverDeliversSettings() throws Exception {
		mvc.perform(get("/settings")).andExpect(status().isOk());
	}

	@Test
	public void serverStoresSettings() throws Exception {
		try (InputStream in = new FileInputStream("src/test/resources/settings.json")) {
			mvc.perform(
					put("/settings").contentType(MediaType.APPLICATION_JSON).content(
							new String(IOUtils.toByteArray(in)))).andExpect(status().isOk());
		}
	}
}
