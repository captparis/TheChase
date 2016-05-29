
package commands;

import java.util.List;
import models.Turn;

public class EndTurnCommand implements ActionCommand{

    private Turn turn;
    
    public EndTurnCommand(Turn turn){
        this.turn = turn;
    }
    
    @Override
    public void execute() {
        //execute all the mode change commands
        List<ModeChangeCommand> modeChangeCommands = turn.getModeChangeCommands();
        
        for(ModeChangeCommand mcc : modeChangeCommands){
            mcc.execute();
        }
    }

    @Override
    public void undo() {
        //execute all the mode change commands
        List<ModeChangeCommand> modeChangeCommands = turn.getModeChangeCommands();
        
        for(ModeChangeCommand mcc : modeChangeCommands){
            mcc.undo();
        }
    }
    
}
