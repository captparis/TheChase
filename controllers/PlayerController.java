/*
 *  OSSD Assignment 1 - The Chase
 *  Charles Yim - S3570764
 *  Jacob Paris - S3238163
 *  Chen Liu- S3481556
 *  Taison Eady - S3282633
 */

//Not sure if this player controller will be needed yet...

package controllers;

import abstractFactory.*;
import models.Unit;
import models.Player;

public class PlayerController {

	GameController gameController;
	UnitController unitController;

	public PlayerController(GameController gameController, UnitController unitController) {
		this.gameController = gameController;
		this.unitController = unitController;
	}

	
	
	public Player newPlayer(String team) throws Exception {

		AbstractPlayerFactory playerFactory;
		
		switch(team){
		case("Guardian"):
			playerFactory = new GuardianPlayerFactory(unitController);
		break;
		case("Explorer"):
			playerFactory = new ExplorerPlayerFactory(unitController);
		break;
		default:
			throw new Exception("Team not implemented");
		}
		
		return playerFactory.getPlayer();

	}




	public void newDiceRoll(Player player, int diceAmount) {
		player.setCurrentRoll(diceAmount);
		player.resetRemainingMoves();
	}
	
	public boolean hasLiveActor(String team){
	    
	    Player player = gameController.getPlayers().get(team);
	    for(Unit unit : player.getUnits().values()){
	        if(unit.isAlive()){
	            return true;
	        }
	    }
	    
	    return false;
	}

}
