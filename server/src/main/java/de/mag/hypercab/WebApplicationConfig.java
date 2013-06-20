package de.mag.hypercab;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "de.mag.hypercab.web", excludeFilters = @Filter({
		Service.class, Configuration.class }))
public class WebApplicationConfig extends WebMvcConfigurerAdapter {

	@Override
	public void configureMessageConverters(
			List<HttpMessageConverter<?>> converters) {
		ByteArrayHttpMessageConverter converter = new ByteArrayHttpMessageConverter();
		List<MediaType> mediaTypes = new ArrayList<MediaType>();
		mediaTypes.add(MediaType.IMAGE_PNG);
		mediaTypes.add(MediaType.IMAGE_JPEG);
		mediaTypes.add(MediaType.IMAGE_GIF);
		converter.setSupportedMediaTypes(mediaTypes);
		converters.add(converter);

		MappingJacksonHttpMessageConverter jacksonConverter = new MappingJacksonHttpMessageConverter();
		converters.add(jacksonConverter);
		super.configureMessageConverters(converters);
	}

}
