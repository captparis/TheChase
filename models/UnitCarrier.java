package models;

import java.io.Serializable;

import javax.swing.ImageIcon;

public class UnitCarrier implements Unit, Serializable {
	
	Unit unit;
	
	public UnitCarrier(Unit unit){
		this.unit = unit;
	}
	
	public void setPassenger(Unit unit){
		this.unit = unit;
	}
	
	public Unit getPassenger(){
		return this.unit;
	}
	
	@Override
	public boolean moveable(int x, int y) {
		return unit.moveable(x, y);
	}

	@Override
	public boolean die(int diceRoll) {
		return unit.die(diceRoll);
	}

	@Override
	public ImageIcon getIcon() {
		return unit.getIcon();
	}

	@Override
	public boolean attackable(int x, int y) {
		return unit.attackable(x, y);
	}

	@Override
	public int getInitX() {
		return unit.getInitX();
	}

	@Override
	public int getInitY() {
		return unit.getInitY();
	}

	@Override
	public void setInitX(int x) {
		unit.setInitX(x);
		
	}

	@Override
	public void setInitY(int y) {
		unit.setInitY(y);
	}

	@Override
	public boolean isAlive() {
		return unit.isAlive();
	}

	@Override
	public void setStatus(boolean alive) {
		unit.setStatus(alive);
	}

	@Override
	public Unit clone() {
		return unit.clone();
	}

	@Override
	public Unit getInnerUnit() {
		return unit.getInnerUnit();
	}

	@Override
	public void setInnerUnit(Unit innerUnit) {
		unit.setInnerUnit(innerUnit);
	}

}
