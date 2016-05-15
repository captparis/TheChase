package models.guardians;

public class Behemoth extends Guardian {

	// create an object of SingleObject
	private static Behemoth instance = new Behemoth();

	private Behemoth() {
		super();
	}

	// Get the only object available
	public static Behemoth getInstance() {
		return instance;
	}

	@Override
	public boolean moveable(int x, int y) {
		// Behemoth can only move left right up down
		if (x == 0 || y == 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean attackable(int x, int y) {
		if (Math.abs(x) > 1 || Math.abs(y) > 1) {
			return false;
		}
		return true;
	}

}
