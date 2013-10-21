package de.mag.hypercab;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
@ComponentScan(basePackages = "de.mag.hypercab.app")
public class ApplicationConfig {

	private static final String HYPERCAB_PROPERTIES_FILENAME = "hypercab.properties";

	@Bean
	public static PropertyPlaceholderConfigurer propertyPlaceholderConfigurer() {
		PropertyPlaceholderConfigurer configurer = new PropertyPlaceholderConfigurer();
		configurer.setLocation(new ClassPathResource(HYPERCAB_PROPERTIES_FILENAME));
		return configurer;
	}

}
