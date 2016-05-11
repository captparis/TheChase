
package decorators;

import javax.swing.ImageIcon;

import models.AbstractUnit;
import models.Unit;

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

	@Override
	public ImageIcon getIcon() {
		return new ImageIcon("bin/images/" + getUnit().toString() + "_agile.png");
	}
    
	public Unit clone(){
		AbstractUnit unitClone = (AbstractUnit) getUnit().clone();
		AbstractUnitDecorator clone = new AgileUnitDecorator(unitClone);
		return clone;
    }
	
}
