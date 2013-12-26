package de.mag.hypercab.app.vpinmame;

public class Rom implements Comparable<Rom> {

	public static final String DMD_X = "dmd_pos_x";
	public static final String DMD_Y = "dmd_pos_y";
	public static final String DMD_WIDTH = "dmd_width";
	public static final String DMD_HEIGHT = "dmd_height";
	public static final String DMD_COMPACT = "dmd_compact";
	public static final String DMD_DOUBLESIZE = "dmd_doublesize";
	public static final String DMD_ROTATION_LEFT = "rol";
	public static final String DMD_ROTATION_RIGHT = "ror";

	private String name;

	private String dmdX;

	private String dmdY;

	private String dmdDoubleSize;

	private String dmdCompact;

	private String dmdWidth;

	private String dmdHeight;

	private String rotateLeft;

	private String rotateRight;

	public String getDmdWidth() {
		return dmdWidth;
	}

	public void setDmdWidth(String dmdWidth) {
		this.dmdWidth = dmdWidth;
	}

	public String getDmdHeight() {
		return dmdHeight;
	}

	public void setDmdHeight(String dmdHeight) {
		this.dmdHeight = dmdHeight;
	}

	public String getDmdDoubleSize() {
		return dmdDoubleSize;
	}

	public void setDmdDoubleSize(String dmdDoubleSize) {
		this.dmdDoubleSize = dmdDoubleSize;
	}

	public String getDmdCompact() {
		return dmdCompact;
	}

	public void setDmdCompact(String dmdCompact) {
		this.dmdCompact = dmdCompact;
	}

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

	public String getRotateLeft() {
		return rotateLeft;
	}

	public void setRotateLeft(String rotateLeft) {
		this.rotateLeft = rotateLeft;
	}

	public String getRotateRight() {
		return rotateRight;
	}

	public void setRotateRight(String rotateRight) {
		this.rotateRight = rotateRight;
	}

	@Override
	public int compareTo(Rom o) {
		return this.name.compareTo(o.getName());
	}
}
