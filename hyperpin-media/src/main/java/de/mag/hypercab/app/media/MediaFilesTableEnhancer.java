package de.mag.hypercab.app.media;

import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import de.mag.hypercab.api.Table;
import de.mag.hypercab.api.TableEnhancer;

@Component
public class MediaFilesTableEnhancer implements TableEnhancer {

	private static final String BACKGLASS_EXISTS = "backglassImage";
	private static final String TABLE_EXISTS = "tableImage";
	private static final String VIDEO_EXISTS = "tableVideo";
	private static final String WHEEL_EXISTS = "wheelImage";

	@Resource
	private MediaService mediaService;

	@Override
	public void enhance(Collection<Table> tables) {
		for (Table table : tables) {
			switch (table.getPlatform()) {
			case VISUAL_PINBALL:
				addVPMediaInfo(table);
				break;
			case FUTURE_PINBALL:
				addFPMediaInfo(table);
				break;
			}
		}
	}

	private void addFPMediaInfo(Table table) {
		table.addAdditional(BACKGLASS_EXISTS, String.valueOf(mediaService.mediaFileExists(
				table.getDescription(), MediaType.FP_BACKGLASS_IMAGE)));
		table.addAdditional(TABLE_EXISTS, String.valueOf(mediaService.mediaFileExists(
				table.getDescription(), MediaType.FP_TABLE_IMAGE)));
		table.addAdditional(WHEEL_EXISTS, String.valueOf(mediaService.mediaFileExists(
				table.getDescription(), MediaType.FP_WHEEL_IMAGE)));
		table.addAdditional(VIDEO_EXISTS, String.valueOf(mediaService.mediaFileExists(
				table.getDescription(), MediaType.FP_TABLE_VIDEO)));
	}

	private void addVPMediaInfo(Table table) {
		table.addAdditional(BACKGLASS_EXISTS, String.valueOf(mediaService.mediaFileExists(
				table.getDescription(), MediaType.VP_BACKGLASS_IMAGE)));
		table.addAdditional(TABLE_EXISTS, String.valueOf(mediaService.mediaFileExists(
				table.getDescription(), MediaType.VP_TABLE_IMAGE)));
		table.addAdditional(WHEEL_EXISTS, String.valueOf(mediaService.mediaFileExists(
				table.getDescription(), MediaType.VP_WHEEL_IMAGE)));
		table.addAdditional(VIDEO_EXISTS, String.valueOf(mediaService.mediaFileExists(
				table.getDescription(), MediaType.VP_TABLE_VIDEO)));
	}
}
