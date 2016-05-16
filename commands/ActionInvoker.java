
package commands;

import java.util.Stack;
import models.Cell;
import models.Turn;
import models.Unit;

public class ActionInvoker {
    
    private static ActionInvoker instance;
    
    private Stack<Turn> turnStack;
    
    private ActionInvoker(){
        this.turnStack = new Stack<>();
    };
    
    public ActionInvoker getInstance(){
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
    
    public void attack(Turn turn, Cell target){
        KillCommand attackCommand = new KillCommand(target);
        attackCommand.execute();
        turn.pushActionCommand(attackCommand);
    }
    
    public void changeMode(Turn turn, Unit unit){
        ModeChangeCommand modeChangeCommand = new ModeChangeCommand();
        turn.addModeChangeCommand(modeChangeCommand);
    }
    
    public void endTurn(Turn turn){
        EndTurnCommand endTurnCommand = new EndTurnCommand(turn);
        endTurnCommand.execute();
        turn.pushActionCommand(endTurnCommand); //TODO not sure if this is needed
        turnStack.push(turn);
    }
    
}
