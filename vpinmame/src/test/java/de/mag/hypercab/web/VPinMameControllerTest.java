package de.mag.hypercab.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.http.MediaType;
import org.testng.annotations.Test;

import de.mag.hypercab.AbstractWebIntegrationTest;

public class VPinMameControllerTest extends AbstractWebIntegrationTest {

	@Test
	public void listsRomNames() throws Exception {
		mvc.perform(
				get("/vpinmame/roms"))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(content().encoding("UTF-8"));
	}

	@Test
	public void respondsWithMethodNotAllowedOnWrongRequestMethod() throws Exception {
		mvc.perform(
				put("/vpinmame/roms"))
				.andExpect(status().isMethodNotAllowed());
	}
}
