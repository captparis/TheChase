
package commands;

import models.Cell;
import models.Unit;

public class KillCommand implements ActionCommand{
    
    private Cell target;
    private Unit unit;
    
    public KillCommand(Cell target){
        this.target = target;
    }

    @Override
    public void execute() {
        unit = target.getUnit();
        unit.setStatus(false);
        target.setUnit(null);
    }

    @Override
    public void undo() {
        target.setUnit(unit);
        unit.setStatus(true);
    }
}
