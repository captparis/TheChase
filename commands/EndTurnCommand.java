
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
