package models.explorers;

public class Hero extends Explorer {

	// create an object of SingleObject
	private static Hero instance = new Hero();

	private Hero() {
		super();
	}

	// Get the only object available
	public static Hero getInstance() {
		return instance;
	}

}
