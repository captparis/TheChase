package abstractFactory;

import controllers.UnitController;
import models.AbstractUnit;
import models.Player;
import models.Settings;
import models.UnitType;

public class GuardianPlayerFactory extends AbstractPlayerFactory {

	public GuardianPlayerFactory(UnitController unitController) {
		super(unitController);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Player getPlayer() {
		String playerName = super.getPlayerName("Guardian");
		Player newPlayer = new Player(playerName, "Guardian");
		initUnits(newPlayer);
		return newPlayer;
	}
	
	void initUnits(Player player) {

		Settings settings = Settings.getInstance();

		try {
	        if (settings.activeUnits.get("golem") == true){
	            UnitType unitType = new UnitType("Golem", "models.guardians", settings.columns - 1, 0);
	            AbstractUnit unit = unitController.newUnit(unitType);
	        	player.addUnit("Golem", unit);
	        }
	        if (settings.activeUnits.get("hunter") == true){
	        	UnitType unitType = new UnitType("Hunter", "models.guardians", 0, settings.rows - 1);
	        	AbstractUnit unit = unitController.newUnit(unitType);
	        	player.addUnit("Hunter", unit);
	        }
	        if (settings.activeUnits.get("behemoth") == true){
	        	UnitType unitType = new UnitType("Behemoth", "models.guardians", 0, 0);
	        	AbstractUnit unit = unitController.newUnit(unitType);
	        	player.addUnit("Behemoth", unit);
	        }

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
