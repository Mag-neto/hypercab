package de.mag.hypercab.app.hyperpin.config;

public enum Section {

	VISUAL_PINBALL("Visual Pinball"), FUTURE_PINBALL("Future Pinball");

	private String sectionName;

	private Section(String name) {
		this.sectionName = name;
	}

	public String getSectionName() {
		return sectionName;
	}
}
