
package commands;

import java.util.Stack;
import models.Cell;
import models.Turn;
import models.Unit;
import models.UnitCarrier;

public class ActionInvoker {
    
    private static ActionInvoker instance;
    
    private Stack<Turn> turnStack;
    
    private ActionInvoker(){
        this.turnStack = new Stack<>();
    };
    
    public static ActionInvoker getInstance(){
        if(instance == null){
            instance = new ActionInvoker();
        }
        return instance;
    }
    
    public void move(Turn turn, Cell origin, Cell target){
        MoveCommand moveCommand = new MoveCommand(origin, target);
        moveCommand.execute();
        turn.pushActionCommand(moveCommand);
    }
    
    public void kill(Turn turn, Cell target){
        KillCommand killCommand = new KillCommand(target);
        killCommand.execute();
        turn.pushActionCommand(killCommand);
    }
    
    public void changeMode(Turn turn, UnitCarrier unitCarrier, Unit newDecoratedUnit ){
        ModeChangeCommand modeChangeCommand = new ModeChangeCommand(unitCarrier, newDecoratedUnit);
        turn.addModeChangeCommand(modeChangeCommand);
    }
    
    public void endTurn(Turn turn){
        EndTurnCommand endTurnCommand = new EndTurnCommand(turn);
        endTurnCommand.execute();
        turn.pushActionCommand(endTurnCommand); //TODO not sure if this is needed
        turnStack.push(turn);
    }
    
}
