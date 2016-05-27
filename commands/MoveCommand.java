
package commands;

import models.Cell;

public class MoveCommand implements ActionCommand{
    private Cell origin;
    private Cell target;
    
    public MoveCommand(Cell origin, Cell target){
        this.origin = origin;
        this.target = target;
    }
    
    @Override
    public void execute() {
        target.setUnitCarrier(origin.getUnitCarrier());
        origin.setUnitCarrier(null);
    }

    @Override
    public void undo() {
        origin.setUnitCarrier(target.getUnitCarrier());
        target.setUnit(null);
    }
    
}
