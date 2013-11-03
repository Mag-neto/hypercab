package de.mag.hypercab.app.media;

public enum MediaType {

	VP_BACKGLASS_IMAGE("Backglass_Image_Path", ".png", true),

	VP_TABLE_IMAGE("Table_Image_Path", ".png", true),

	VP_TABLE_VIDEO("Table_Video_Path", ".f4v", true),

	FP_BACKGLASS_IMAGE("Backglass_Image_Path", ".png", true),

	FP_TABLE_IMAGE("Table_Image_Path", ".png", true),

	FP_TABLE_VIDEO("Table_Video_Path", ".f4v", true),

	VP_WHEEL_IMAGE("Media/Visual Pinball/Wheel Images/", ".png", false),

	FP_WHEEL_IMAGE("Media/Future Pinball/Wheel Images/", ".png", false),

	HP_FLYER_IMAGE("Media/HyperPin/Flyer Images/", ".jpg", false);

	private MediaType(String mediaPath, String extension, boolean resolveFromConfig) {
		this.mediaPath = mediaPath;
		this.extension = extension;
		this.resolveFromConfig = resolveFromConfig;
	}

	private final String mediaPath;
	private final String extension;
	private final boolean resolveFromConfig;

	public String mediaPath() {
		return mediaPath;
	}

	public String extension() {
		return extension;
	}

	public boolean isVPMediaType() {
		return this.name().startsWith("VP");
	}

	public boolean isFPMediaType() {
		return this.name().startsWith("FP");
	}

	public boolean isResolveFromConfig() {
		return this.resolveFromConfig;
	}
}
