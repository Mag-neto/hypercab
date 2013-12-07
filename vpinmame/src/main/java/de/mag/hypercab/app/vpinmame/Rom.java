package de.mag.hypercab.app.vpinmame;

public class Rom implements Comparable<Rom> {

	public static final String DMD_X = "dmd_pos_x";
	public static final String DMD_Y = "dmd_pos_y";

	private String name;

	private String dmdX;

	private String dmdY;

	public String getName() {
		return name;
	}

	public String getDmdX() {
		return dmdX;
	}

	public void setDmdX(String dmdX) {
		this.dmdX = dmdX;
	}

	public String getDmdY() {
		return dmdY;
	}

	public void setDmdY(String dmdY) {
		this.dmdY = dmdY;
	}

	public Rom withName(String name) {
		this.name = name;
		return this;
	}

	@Override
	public int compareTo(Rom o) {
		return this.name.compareTo(o.getName());
	}
}
