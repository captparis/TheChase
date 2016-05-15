package models.explorers;

public class TrapMaster extends Explorer {

	// create an object of SingleObject
	private static TrapMaster instance = new TrapMaster();

	private TrapMaster() {
		super();
	}

	// Get the only object available
	public static TrapMaster getInstance() {
		return instance;
	}
}
