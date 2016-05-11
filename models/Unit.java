
package models;

import javax.swing.ImageIcon;

public interface Unit {
    
    public abstract boolean moveable(int x, int y);
    
    public abstract boolean die(int diceRoll);
    
	public abstract ImageIcon getIcon(); 

	public abstract boolean attackable(int x, int y);

	public abstract int getInitX();

	public abstract int getInitY();

	public abstract void setInitX(int x);
	
	public abstract void setInitY(int y);

	public abstract String toString();

	public abstract boolean isAlive();

	public abstract void setStatus(boolean alive);
        
	public abstract Unit clone();
    
}
