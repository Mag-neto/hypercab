package de.mag.hypercab.api.ini;

import java.util.ArrayList;
import java.util.List;

public class SectionVO {

	private String name;
	private List<KeyValuePair> configs = new ArrayList<>();

	public List<KeyValuePair> getConfigs() {
		return configs;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setConfigs(List<KeyValuePair> configs) {
		this.configs = configs;
	}

}
