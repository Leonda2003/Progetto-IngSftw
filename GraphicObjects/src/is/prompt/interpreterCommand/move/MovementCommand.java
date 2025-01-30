package is.prompt.interpreterCommand.move;

import is.prompt.interpreterCommand.AbstractCommand;
import is.prompt.interpreterCommand.PosCommand;
import is.prompt.interpreterCommand.terminal.ObjID;

public abstract class MovementCommand extends AbstractCommand {

    ObjID objID;
    PosCommand pos;


    public ObjID getObjID() {
        return objID;
    }

    public PosCommand getPos() {
        return pos;
    }
}
