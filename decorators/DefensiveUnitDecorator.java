
package decorators;

import models.AbstractUnit;

public class DefensiveUnitDecorator extends AbstractUnitDecorator{

    public DefensiveUnitDecorator(AbstractUnit unit) {
        super(unit);
    }

    @Override
    public boolean moveable(int x, int y) {
        boolean moveable = unit.moveable(x,y);
        
        if(!moveable){
            return false;
        }else if(Math.abs(x) > 3 || Math.abs(y) > 3){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public boolean die(int diceRoll) {
        if(diceRoll == 6){
            return true;
        }
        return false;
    }
    
}
