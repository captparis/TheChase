package models.explorers;

import models.Unit;

public class Scout extends Explorer {

	// create an object of SingleObject
	private static Scout instance = new Scout();

	public Scout() {
		super();
	}

	// Get the only object available
	public static Scout getInstance() {
		return instance;
	}


}
