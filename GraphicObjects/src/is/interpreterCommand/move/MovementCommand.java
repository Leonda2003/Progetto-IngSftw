package is.interpreterCommand.move;

import is.interpreterCommand.AbstractCommand;
import is.interpreterCommand.PosCommand;
import is.interpreterCommand.terminal.ObjID;
import is.interpreterCommand.terminal.TerminalCommand;

public abstract class MovementCommand extends AbstractCommand {

    ObjID objID;
    PosCommand pos;


}
