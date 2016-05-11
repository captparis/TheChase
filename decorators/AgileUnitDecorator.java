
package decorators;

import models.AbstractUnit;

public class AgileUnitDecorator extends AbstractUnitDecorator{

    public AgileUnitDecorator(AbstractUnit unit) {
        super(unit);
    }

    @Override
    public boolean moveable(int x, int y) {
        return super.getUnit().moveable(x,y);
    }

    @Override
    public boolean die(int diceRoll) {
        if(diceRoll > 3){
            return true;
        }
        return false;
    }
    
}
