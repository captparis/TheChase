/*
 *  OSSD Assignment 1 - The Chase
 *  Charles Yim - S3570764
 *  Jacob Paris - S3238163
 *  Chen Liu- S3481556
 *  Taison Eady - S3282633
 */
package models;

import java.util.*;

public class Player {

    private final String name;
    private final String team;
    public Map<String, Actor> actors;
    private int currentRoll;
    private int remainingMoves;
    
    public Player(String name, String team) {
        this.name = name;
        this.team = team;
        this.actors = new HashMap<>();
    }
    
    public String getName(){
        return name;
    }
    
    public String getTeam() {
        return team;
    }

    public Unit addActor(String actorType, Actor newActor)throws Exception{
        Unit existingUnit = actors.put(actorType, newActor);
        
        if(existingUnit == null){
            return newActor;
        }else{
            throw new Exception("Unit already exists.");
        }
    }
    
    public Unit getUnit(String unitType)throws Exception{
        Unit unit = actors.get(unitType);
        if(unit == null){
            throw new Exception("No such unit type.");
        }else{
            return unit;
        }
    }

    public int getCurrentRoll() {
        return currentRoll;
    }

    public void setCurrentRoll(int currentRoll) {
        this.currentRoll = currentRoll;
    }

    public int getRemainingMoves() {
        return remainingMoves;
    }

    public void resetRemainingMoves(){
        remainingMoves = currentRoll;
    }
    
    public void subtractRemainingMoves(int movesToSubtract) throws Exception {
        this.remainingMoves -= movesToSubtract;
        if(this.remainingMoves <0){
        	throw new Exception("Moved TOOOOO Far!");
        }
    }

    public boolean hasActor(Actor actor) {
        return actors.containsValue(actor);
    }
          
}
