package is.interpreterCommand.move;

import is.interpreterCommand.PosCommand;
import is.interpreterCommand.terminal.ObjID;
import is.interpreterCommand.terminal.TerminalCommand;

public class MoveOffCommand extends MovementCommand{

    TerminalCommand mv;

    MoveOffCommand(TerminalCommand mv, PosCommand pos, ObjID objID){
        this.mv = mv;
        super.objID = objID;
        super.pos = pos;
    }
}
