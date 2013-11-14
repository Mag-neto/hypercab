package de.mag.hypercab.app.media;

import java.io.File;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import de.mag.hypercab.app.hyperpin.config.Configuration;
import de.mag.hypercab.app.hyperpin.config.Section;

@Component
public class MediaPathResolver {

	@Resource
	private Configuration configuration;

	File resolveMediaPath(String tableRef, MediaType type) {
		String path = "";

		if (type.isVPMediaType()) {
			if (type.isResolveFromConfig()) {
				path = configuration.getSetting(Section.VISUAL_PINBALL, type.mediaPath());
			} else {
				path = configuration.getHyperpinPath().getAbsolutePath() + File.separator
						+ type.mediaPath();
			}
		} else if (type.isFPMediaType()) {
			if (type.isResolveFromConfig()) {
				path = configuration.getSetting(Section.FUTURE_PINBALL, type.mediaPath());
			} else {
				path = configuration.getHyperpinPath().getAbsolutePath() + File.separator
						+ type.mediaPath();
			}
		}

		return new File(path + tableRef + type.extension());
	}

}
