
package decorators;

import javax.swing.ImageIcon;

import models.AbstractUnit;
import models.Unit;

public class AgileUnitDecorator extends AbstractUnitDecorator{

    public AgileUnitDecorator(Unit unit) {
        super(unit);
    }

    @Override
    public boolean moveable(int x, int y) {
        return super.initMoveable(x, y);
    }

    @Override
    public boolean die(int diceRoll) {
        if(diceRoll > 2){
            return true;
        }
        return false;
    }

	@Override
	public ImageIcon getIcon() {
		return new ImageIcon("bin/images/" + getInnerUnit().toString() + "_agile.png");
	}
    
	public Unit clone(){
		AbstractUnit unitClone = (AbstractUnit) getInnerUnit().clone();
		AbstractUnitDecorator clone = new AgileUnitDecorator(unitClone);
		return clone;
    }

 

    


	
}
