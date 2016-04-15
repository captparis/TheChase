/*
 *  OSSD Assignment 1 - The Chase
 *  Charles Yim - S3570764
 *  Jacob Paris - S3238163
 *  Chen Liu- S3481556
 *  Taison Eady - S3282633
 */

//Not sure if this player controller will be needed yet...

package controllers;

import javax.swing.*;

import models.ActorType;
import models.Player;

public class PlayerController {
    
    GameController gameController;
    UnitController unitController;

    public PlayerController(GameController gameController, UnitController unitController) {
        this.gameController = gameController;
        this.unitController = unitController;
    }
    
    public Player newPlayer(String team){

        boolean accepted = false;
        String name = null;
        
        while(!accepted) {            
            try {
                name = validatedName(getNameInput(team));
                accepted = true;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, 
                        "Your name must be between 1 and 50 characters long.");
            }
        }
        Player newPlayer = new Player(name, team);
        initUnits(newPlayer);
        return newPlayer;
    }
    
    private void initUnits(Player player){

    	for(ActorType actorType : gameController.getTeamSetup().get(player.getTeam())){
    		try {
    			String actorTypeName = actorType.getType();
				player.addActor(actorTypeName, unitController.newActor(actorType));
			} catch (Exception e) {
				e.printStackTrace();
			}

    	}
    }

    private String getNameInput(String team){
        String playerName = (String)JOptionPane.showInputDialog(
        "What is your name " + team + "?");
        
        return playerName;
    }

    private String validatedName(String nameInput) throws Exception{
        if(nameInput.length() < 1 || nameInput.length() > 50){
            throw new Exception("Name length is out of bounds");
        }
        return nameInput;
    }
    
    public void newDiceRoll(Player player, int diceAmount){
        player.setCurrentRoll(diceAmount);
        player.resetRemainingMoves();
    }
    
}
