
package models;

public interface Unit {
    
    public abstract boolean moveable(int x, int y);
    
    public abstract boolean die(int diceRoll);
    
}
