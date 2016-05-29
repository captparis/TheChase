
package models;

import commands.*;

import java.io.Serializable;
import java.util.EmptyStackException;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class Turn implements  Serializable {
    private Player player;
    private Stack<ActionCommand> actions;
    private Queue<ModeChangeCommand> modeChanges;
    
    public Turn(Player player){
        this.player = player;
        actions = new Stack<>();
        modeChanges = new LinkedList<>();
    }
    
    //Adds an action object to the top of the stack
    public void pushActionCommand(ActionCommand action) {
        actions.push(action);
    }
    
    //returns the most recent action object and null if the stack is empty
    public ActionCommand popActionCommand(){
        try{
            return actions.pop();
        }catch(EmptyStackException e){
            return null;
        }
    }
    
    public void addModeChangeCommand(ModeChangeCommand modeChangeCommand){
        this.modeChanges.add(modeChangeCommand);
    }
    
    public Queue<ModeChangeCommand> getModeChangeCommands(){
        return this.modeChanges;
    }

	public boolean hasActionCommand() {
		return !actions.isEmpty();
	}
    
}
