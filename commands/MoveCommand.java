
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
        target.setUnit(origin.getUnit());
        origin.setUnit(null);
    }

    @Override
    public void undo() {
        origin.setUnit(target.getUnit());
        target.setUnit(null);
    }
    
}
