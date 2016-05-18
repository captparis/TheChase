package models.guardians;

import models.Unit;

public class Golem extends Guardian {

	// create an object of SingleObject
	private static Golem instance = new Golem();

	public Golem() {
		super();
	}

	// Get the only object available
	public static Golem getInstance() {
		return instance;
	}

	@Override
	public boolean initMoveable(int x, int y) {

		if (Math.abs(x) > 2 || Math.abs(y) > 2) {
			return false;
		} else {
			return true;
		}

	}

	@Override
	public boolean attackable(int x, int y) {
		if ((Math.abs(x) == 2 || Math.abs(y) == 2) && Math.abs(x) != Math.abs(y)) {
			return true;
		}
		return false;
	}

}
