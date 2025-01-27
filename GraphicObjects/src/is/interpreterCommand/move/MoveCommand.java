package is.interpreterCommand.move;

import is.interpreterCommand.PosCommand;
import is.interpreterCommand.terminal.ObjID;
import is.interpreterCommand.terminal.TerminalCommand;

public class MoveCommand extends MovementCommand{

    TerminalCommand mvoff;

    public MoveCommand(TerminalCommand mvoff,ObjID objID,PosCommand pos){
        this.mvoff = mvoff;
        super.objID = objID;
        super.pos = pos;
    }

    @Override
    public String toString() {
        return "MovementCommand: "+ mvoff + objID + pos;
    }
}
