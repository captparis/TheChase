
package commands;

import models.Unit;
import models.UnitCarrier;

public class ModeChangeCommand implements ActionCommand{

	UnitCarrier unitCarrier;
	Unit oldDecoratedUnit;
	Unit newDecoratedUnit;
	
	public ModeChangeCommand(UnitCarrier unitCarrier, Unit newDecoratedUnit){
		this.unitCarrier = unitCarrier;
		this.newDecoratedUnit = newDecoratedUnit;
		this.oldDecoratedUnit = this.unitCarrier.getPassenger();
	}
	
    @Override
    public void execute() {
        newDecoratedUnit.setInnerUnit(oldDecoratedUnit.getInnerUnit());
    	unitCarrier.setPassenger(newDecoratedUnit);
    	System.out.println("Mode changed for: " + unitCarrier.getPassenger());
    }

    @Override
    public void undo() {
        unitCarrier.setPassenger(oldDecoratedUnit);
    }
    
}
