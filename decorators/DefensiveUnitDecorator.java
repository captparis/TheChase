
package decorators;

import javax.swing.ImageIcon;

import models.AbstractUnit;
import models.Unit;

public class DefensiveUnitDecorator extends AbstractUnitDecorator{

    public DefensiveUnitDecorator(Unit unit) {
        super(unit);
    }

    public DefensiveUnitDecorator() {
		super();
	}

	@Override
    public boolean moveable(int x, int y) {
        boolean moveable = super.getInnerUnit().moveable(x,y);
        
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
        if(diceRoll == 6 || diceRoll == 5){
            return true;
        }
        return false;
    }

	@Override
	public ImageIcon getIcon() {
		return new ImageIcon("bin/images/" + getInnerUnit().toString() + "_defense.png");
	}
    
	
	public Unit clone(){
		AbstractUnit unitClone = (AbstractUnit) getInnerUnit().clone();
		AbstractUnitDecorator clone = new DefensiveUnitDecorator(unitClone);
		return clone;
    }


}
