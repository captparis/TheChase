
package commands;

import java.util.Queue;

import models.Turn;

public class EndTurnCommand implements ActionCommand{

    private Turn turn;
    
    public EndTurnCommand(Turn turn){
        this.turn = turn;
    }
    
    @Override
    public void execute() {
        //execute all the mode change commands
        Queue<ModeChangeCommand> modeChangeCommands = turn.getModeChangeCommands();
        
        for(ModeChangeCommand mcc : modeChangeCommands){
            mcc.execute();
            System.out.println("Mode Changed with:" + mcc.toString());
        }
    }

    @Override
    public void undo() {
        //execute all the mode change commands
        Queue<ModeChangeCommand> modeChangeCommands = turn.getModeChangeCommands();
        
        for(ModeChangeCommand mcc : modeChangeCommands){
            mcc.undo();
        }
    }
    
}
