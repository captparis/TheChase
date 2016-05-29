package models;

import java.util.HashMap;
import java.util.Map;

public class Settings {
	
	public int rows = 8;
	public int columns = 8;
	
	public Boolean setup = false;
	
	public Map<String, Boolean> activeUnits = new HashMap<String, Boolean>();
	
	//Settings singleton
	private static Settings instance = new Settings();



	//Get settings instance or create an instance
	public static Settings getInstance() {
			return instance;
	}

	public Settings() {
		activeUnits.put("golem", true);
		activeUnits.put("behemoth", true);
		activeUnits.put("hunter", true);
		activeUnits.put("hero", true);
		activeUnits.put("scout", true);
		activeUnits.put("tactician", true);
		activeUnits.put("trapmaster", true);
	}
	
	public void setBoardSize(int rows, int columns){
		this.rows = rows;
		this.columns = columns;
	}


}
