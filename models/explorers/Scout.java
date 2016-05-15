package models.explorers;

public class Scout extends Explorer {

	// create an object of SingleObject
	private static Scout instance = new Scout();

	private Scout() {
		super();
	}

	// Get the only object available
	public static Scout getInstance() {
		return instance;
	}

}
