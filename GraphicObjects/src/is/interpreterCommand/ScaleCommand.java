package is.interpreterCommand;

import is.interpreterCommand.terminal.ObjID;
import is.interpreterCommand.terminal.Posfloat;
import is.interpreterCommand.terminal.TerminalCommand;

public class ScaleCommand extends AbstractCommand{

    TerminalCommand scale;
    ObjID objID;
    Posfloat posfloat;

    public ScaleCommand(TerminalCommand scale, ObjID objID, Posfloat posfloat) {
        this.scale = scale;
        this.objID = objID;
        this.posfloat = posfloat;
    }
}
