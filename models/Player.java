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
	private Map<String, AbstractUnit> units;
	private int currentRoll;
	private int remainingMoves;

	public Player(String name, String team) {
		this.name = name;
		this.team = team;
		this.units = new HashMap<>();
	}

	public String getName() {
		return name;
	}

	public String getTeam() {
		return team;
	}
	
	public Map<String,AbstractUnit> getUnits(){
	    return units;
	}

	public AbstractUnit addUnit(String unitType, AbstractUnit newUnit) throws Exception {
		AbstractUnit existingUnit = units.put(unitType, newUnit);

		if (existingUnit == null) {
			return newUnit;
		} else {
			throw new Exception("Unit already exists.");
		}
	}

	public AbstractUnit getUnit(String unitType) throws Exception {
		AbstractUnit unit = units.get(unitType);
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

	public boolean hasUnit(AbstractUnit unit) {
		return units.containsValue(unit);
	}
	
	public Player clone(){
        try {
            return (Player) super.clone();
        } catch (CloneNotSupportedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

}
