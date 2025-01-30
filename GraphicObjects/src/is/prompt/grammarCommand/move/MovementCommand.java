package is.prompt.grammarCommand.move;

import is.prompt.grammarCommand.AbstractCommand;
import is.prompt.grammarCommand.PosCommand;
import is.prompt.grammarCommand.terminal.ObjID;

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
