package is.interpreterCommand.move;

import is.interpreterCommand.PosCommand;
import is.interpreterCommand.terminal.ObjID;
import is.interpreterCommand.terminal.TerminalCommand;

public class MoveOffCommand extends MovementCommand{

    TerminalCommand mv;

    public MoveOffCommand(TerminalCommand mv,  ObjID objID, PosCommand pos){
        this.mv = mv;
        super.objID = objID;
        super.pos = pos;
    }
}
