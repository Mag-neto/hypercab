package de.mag.hypercab.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.annotation.Resource;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import de.mag.hypercab.AbstractWebIntegrationTest;

public class TableControllerTest extends AbstractWebIntegrationTest {

	@Resource
	WebApplicationContext wac;

	MockMvc mvc;

	@BeforeClass
	public void setup() {
		mvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void deliversTables() throws Exception {
		mvc.perform(get("/tables")).andExpect(status().isOk());
	}

	@Test
	public void deliversActiveTables() throws Exception {
		mvc.perform(get("/tables/active")).andExpect(status().isOk());
	}

	@Test
	public void deliversInactiveTables() throws Exception {
		mvc.perform(get("/tables/inactive")).andExpect(status().isOk());
	}

}
