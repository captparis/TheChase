
package commands;

import java.util.EmptyStackException;
import java.util.Stack;
import javax.swing.JOptionPane;
import controllers.GameController;
import models.Cell;
import models.Player;
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
        turn.pushActionCommand(endTurnCommand); 
    }
    
    private void undoAction(Turn turn){    	
    	ActionCommand command = turn.popActionCommand();
    	
    	if(command == null ){
    		JOptionPane.showMessageDialog(null, "There are no more actions in this turn");
    	}else{
    		command.undo();
    	}
    }
    
    public void undoAction(){

    	Turn turn;
		try {
			turn = turnStack.peek();
		} catch (EmptyStackException e) {
			JOptionPane.showMessageDialog(null,"There are no actions to undo yet.");
			return;
		}
    	
    	ActionCommand command = turn.popActionCommand();
    	
    	if(command == null ){
    		JOptionPane.showMessageDialog(null, "There are no more actions in this turn");
    	}else{
    		command.undo();
    	}
    	turn.getPlayer().setUndoAllowed(false);
    }
    
    public void undoTurn(){
	    
    	Player player;
		try {
			player = turnStack.peek().getPlayer();
		} catch (EmptyStackException e) {
			JOptionPane.showMessageDialog(null,"There are no turns to undo yet.");
			return;
		}
    	
    	if (player.getTurnsUndone() > 2){
    		JOptionPane.showMessageDialog(null, "You have undone the maximum number of turns");
    		return;
    	}
    	
		if(turnStack.size()<3){
			JOptionPane.showMessageDialog(null, "Can't undo past the start of the game...");
			return;
		}
    	//Undo the current player's turn
    	Turn turn = turnStack.pop();
    	while(turn.hasActionCommand()){
    		undoAction(turn);
    	};
    	
    	//Undo the other player's turn
    	turn = turnStack.pop();
    	while(turn.hasActionCommand()){
    		undoAction(turn);
    	};
    	
    	player.incrementTurnsUndone();
    	player.setUndoAllowed(false);

    }
}
