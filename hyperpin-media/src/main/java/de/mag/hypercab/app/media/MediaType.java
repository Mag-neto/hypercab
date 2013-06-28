package de.mag.hypercab.app.media;

public enum MediaType {

	VP_BACKGLASS_IMAGE("Media/Visual Pinball/Backglass Images/", ".png"),

	VP_TABLE_IMAGE("Media/Visual Pinball/Table Images/", ".png"),

	VP_WHEEL_IMAGE("Media/Visual Pinball/Wheel Images/", ".png"),

	VP_TABLE_VIDEO("Media/Visual Pinball/Table Videos/", ".f4v"),

	FP_BACKGLASS_IMAGE("Media/Future Pinball/Backglass Images/", ".png"),

	FP_TABLE_IMAGE("Media/Future Pinball/Table Images/", ".png"),

	FP_WHEEL_IMAGE("Media/Future Pinball/Wheel Images/", ".png"),

	FP_TABLE_VIDEO("Media/Future Pinball/Table Videos/", ".f4v"),

	HP_FLYER_IMAGE("Media/HyperPin/Flyer Images/", ".jpg");

	private MediaType(String mediaPath, String extension) {
		this.mediaPath = mediaPath;
		this.extension = extension;
	}

	private final String mediaPath;
	private final String extension;

	public String mediaPath() {
		return mediaPath;
	}

	public String extension() {
		return extension;
	}
}
