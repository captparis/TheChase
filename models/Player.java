/*
 *  OSSD Assignment 1 - The Chase
 *  Charles Yim - S3570764
 *  Jacob Paris - S3238163
 *  Chen Liu- S3481556
 *  Taison Eady - S3282633
 */
package models;

import java.io.Serializable;
import java.util.*;

public class Player implements Serializable{

	private final String name;
	private final String team;
	private Map<String, Unit> units;
	private int currentRoll;
	private int remainingMoves;
	private boolean undoAllowed;
	private int turnsUndone;

	public Player(String name, String team) {
		this.name = name;
		this.team = team;
		this.units = new HashMap<>();
		
		this.undoAllowed = true;
		this.turnsUndone =0;
		
	}

	public String getName() {
		return name;
	}

	public String getTeam() {
		return team;
	}
	
	public Map<String,Unit> getUnits(){
	    return units;
	}

	public Unit addUnit(String unitType, Unit newUnit) throws Exception {
		Unit existingUnit = units.put(unitType, newUnit);

		if (existingUnit == null) {
			return newUnit;
		} else {
			throw new Exception("Unit already exists.");
		}
	}

	public Unit getUnit(String unitType) throws Exception {
		Unit unit = units.get(unitType);
		if (unit == null) {
			throw new Exception("No such unit type.");
		} else {
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

	public void resetRemainingMoves() {
		remainingMoves = currentRoll;
	}

	public void subtractRemainingMoves(int movesToSubtract) throws Exception {
		this.remainingMoves -= movesToSubtract;
		if (this.remainingMoves < 0) {
			throw new Exception("Moved TOOOOO Far!");
		}
	}

	public boolean hasUnit(Unit unit) {
		if(unit == null){
			return false;
		}
		return units.containsValue(unit.getInnerUnit());
	}

	public int getTurnsUndone() {
		return turnsUndone;
	}

	public void incrementTurnsUndone() {
		this.turnsUndone++;
	}

	public boolean isUndoAllowed() {
		return undoAllowed;
	}

	public void setUndoAllowed(boolean undoAllowed) {
		this.undoAllowed = undoAllowed;
	}
}
