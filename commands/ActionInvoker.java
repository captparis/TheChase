
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
    
    public void startTurn(Turn turn){
        turnStack.push(turn);
    }
    
    public void move(Cell origin, Cell target){
        MoveCommand moveCommand = new MoveCommand(origin, target);
        moveCommand.execute();
        
        Turn turn = turnStack.peek();
        turn.pushActionCommand(moveCommand);
    }
    
    public void kill(Cell target){
        KillCommand killCommand = new KillCommand(target);
        killCommand.execute();
        
        Turn turn = turnStack.peek();
        turn.pushActionCommand(killCommand);
    }
    
    public void changeMode(UnitCarrier unitCarrier, Unit newDecoratedUnit ){
        ModeChangeCommand modeChangeCommand = new ModeChangeCommand(unitCarrier, newDecoratedUnit);
        
        Turn turn = turnStack.peek();
        turn.addModeChangeCommand(modeChangeCommand);
    }
    
    public void endTurn(){
        Turn turn = turnStack.peek();
    	EndTurnCommand endTurnCommand = new EndTurnCommand(turn);
        endTurnCommand.execute();
        turn.pushActionCommand(endTurnCommand); //TODO not sure if this is needed
    }
    
    public void undoAction(){
    	turnStack.peek().popActionCommand().undo();
    }
    public void undoTurn(){
    	Turn turn = turnStack.pop();
    	while(turn.hasActionCommand()){
    		ActionCommand command = turn.popActionCommand();
    		command.undo();
    	};
    	
    }
}
