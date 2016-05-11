
package decorators;

import javax.swing.ImageIcon;

import models.AbstractUnit;
import models.Unit;

public abstract class AbstractUnitDecorator implements Unit{
    private AbstractUnit unit;

    public AbstractUnitDecorator(AbstractUnit unit){
        this.unit = unit;
    }
    
    public AbstractUnit getUnit() {
        return unit;
    }
    
    //declare abstract methods to be changed using decorator.
    public abstract boolean moveable(int x, int y);
    public abstract boolean die(int diceRoll);
	public abstract ImageIcon getIcon();
	public abstract Unit clone();
    
    //Forward all undecorated method calls to the base unit.
	public int getInitX() {
		return unit.getInitX();
	}

	public int getInitY() {
		return unit.getInitY();
	}

	public void setInitX(int x) {
		unit.setInitX(x);
	}

	public void setInitY(int y) {
		unit.setInitY(y);
	}
    
	@Override
	public String toString() {
		return unit.toString();
	}

	public boolean isAlive() {
		return unit.isAlive();
	}

	public void setStatus(boolean alive) {
		unit.setStatus(alive);
	}
	
	public boolean attackable(int x, int y){
		return unit.attackable(x, y);
	}
    
}
