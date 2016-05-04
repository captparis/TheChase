package models;

public class UnitType {

	private String type;
	private String pack;
	private int initX;
	private int initY;

	public UnitType(String type, String pack, int initX, int initY) {
		this.type = type;
		this.pack = pack;
		this.initX = initX;
		this.initY = initY;
	}

	public String getType() {
		return type;
	}

	public String getPack() {
		return pack;
	}

	public int getInitX() {
		return initX;
	}

	public int getInitY() {
		return initY;
	}

	public String getQualifiedName() {
		return pack + "." + type;
	}
}
