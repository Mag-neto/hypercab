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
		String path = null;
		if (type.isVPMediaType()) {
			path = resolvePath(Section.VISUAL_PINBALL, type);
		} else if (type.isFPMediaType()) {
			path = resolvePath(Section.FUTURE_PINBALL, type);
		}

		return new File(path + tableRef + type.extension());
	}

	private String resolvePath(Section section, MediaType type) {
		String path = "";
		if (type.isResolveFromConfig()) {
			path = configuration.getSetting(section, type.mediaPath());
		} else {
			path = configuration.getHyperpinPath().getAbsolutePath() + File.separator
					+ type.mediaPath();
		}
		return path;
	}

}
