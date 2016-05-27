
package commands;

import models.Cell;
import models.Unit;
import models.UnitCarrier;

public class KillCommand implements ActionCommand{
    
    private Cell target;
    private UnitCarrier unitCarrier;
    
    public KillCommand(Cell target){
        this.target = target;
    }

    @Override
    public void execute() {
        unitCarrier = target.getUnitCarrier();
        unitCarrier.setStatus(false);
        target.setUnitCarrier(null);
    }

    @Override
    public void undo() {
        target.setUnitCarrier(unitCarrier);
        unitCarrier.setStatus(true);
    }
}
