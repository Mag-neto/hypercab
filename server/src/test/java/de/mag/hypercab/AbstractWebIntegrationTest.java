package de.mag.hypercab;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Base class to derive concrete web test classes from.
 * 
 */
@WebAppConfiguration
@ContextHierarchy({ @ContextConfiguration(classes = ApplicationConfig.class),
		@ContextConfiguration(classes = WebApplicationConfig.class) })
public abstract class AbstractWebIntegrationTest extends
		AbstractTestNGSpringContextTests {

}
