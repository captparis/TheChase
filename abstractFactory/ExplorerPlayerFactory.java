package abstractFactory;

import controllers.UnitController;
import models.AbstractUnit;
import models.Player;
import models.Settings;
import models.UnitType;


public class ExplorerPlayerFactory extends AbstractPlayerFactory {


	public ExplorerPlayerFactory(UnitController unitController) {
		super(unitController);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Player getPlayer() {
		
		String playerName = super.getPlayerName("Explorer");
		Player newPlayer = new Player(playerName, "Explorer");
		initUnits(newPlayer);
		return newPlayer;
	}

	void initUnits(Player player) {
		Settings settings = Settings.getInstance();

		try {
	        if (settings.activeUnits.get("hero") == true){
	            UnitType unitType = new UnitType("Hero", "models.explorers", settings.columns - 2, settings.rows - 1);
	            AbstractUnit unit = unitController.newUnit(unitType);
	        	player.addUnit("Hero", unit);
	        }
	        if (settings.activeUnits.get("trapmaster") == true){
	        	UnitType unitType = new UnitType("TrapMaster", "models.explorers", settings.columns - 2, settings.rows - 2);
	        	AbstractUnit unit = unitController.newUnit(unitType);
	        	player.addUnit("TrapMaster", unit);
	        }
	        if (settings.activeUnits.get("tactician") == true){
	        	UnitType unitType = new UnitType("Tactician", "models.explorers", settings.columns - 1, settings.rows - 1);
	        	AbstractUnit unit = unitController.newUnit(unitType);
	        	player.addUnit("Tactician", unit);
	        }
	        if (settings.activeUnits.get("scout") == true){
	        	UnitType unitType = new UnitType("Scout", "models.explorers", settings.columns - 1, settings.rows - 2);
	        	AbstractUnit unit = unitController.newUnit(unitType);
	        	player.addUnit("Scout", unit);
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
