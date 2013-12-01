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

	public String getConfig(String key) {
		for (KeyValuePair pair : configs) {
			if (pair.getKey().equalsIgnoreCase(key)) {
				return pair.getValue();
			}
		}
		return null;
	}

	public void setConfig(String key, String value) {
		KeyValuePair config = null;
		for (KeyValuePair pair : configs) {
			if (pair.getKey().equalsIgnoreCase(key)) {
				config = pair;
				break;
			}
		}
		if (config == null) {
			config = new KeyValuePair();
			config.setKey(key);
			configs.add(config);
		}
		config.setValue(value);
	}

}
