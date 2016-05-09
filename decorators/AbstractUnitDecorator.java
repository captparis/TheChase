
package decorators;

import models.AbstractUnit;
import models.Unit;

public abstract class AbstractUnitDecorator implements Unit{
    protected AbstractUnit unit;
    
    public AbstractUnitDecorator(AbstractUnit unit){
        this.unit = unit;
    }
    
    public abstract boolean moveable(int x, int y);
    public abstract boolean die(int diceRoll);
}
