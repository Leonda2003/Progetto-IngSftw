package is.interpreterCommand.move;

import is.interpreterCommand.PosCommand;
import is.interpreterCommand.terminal.ObjID;
import is.interpreterCommand.terminal.TerminalCommand;

public class MoveCommand extends MovementCommand{

    TerminalCommand mvoff;

    MoveCommand(TerminalCommand mvoff, PosCommand pos, ObjID objID){
        this.mvoff = mvoff;
        super.objID = objID;
        super.pos = pos;
    }
}
