package models;

public class Settings {
	
	public int rows = 8;
	public int columns = 8;
	
	// create an object of settings
	private static Settings instance = new Settings();

	// Get the only object available
	public static Settings getInstance() {
			return instance;
	}

	public Settings() {
		
	}
	
	public void setBoardSize(int rows, int columns){
		this.rows = rows;
		this.columns = columns;
	}

}
