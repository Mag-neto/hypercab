package de.mag.hypercab.api;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Table model class. Basic Database xml data is stored in attributes.
 * Additional data, such as media file links and availability information is
 * added by {@link TableEnhancer} implementations.
 * 
 * @author marco
 * 
 */
public class Table {

	private Platform platform;

	// database.xml elements
	private String description;
	private String fileName;
	private String year;
	private String machineType;
	private String manufacturer;

	// table status
	private boolean active;

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	private Map<String, String> additional = new HashMap<>();

	public void addAdditional(String key, String value) {
		this.additional.put(key, value);
	}

	public String getAdditional(String key) {
		return additional.get(key);
	}

	public Map<String, String> getAdditionals() {
		return Collections.unmodifiableMap(additional);
	}

	public Platform getPlatform() {
		return platform;
	}

	public void setPlatform(Platform platform) {
		this.platform = platform;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMachineType() {
		return machineType;
	}

	public void setMachineType(String machineType) {
		this.machineType = machineType;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

}
