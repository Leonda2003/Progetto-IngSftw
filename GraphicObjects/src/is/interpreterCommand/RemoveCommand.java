package is.interpreterCommand;

import is.interpreterCommand.terminal.ObjID;
import is.interpreterCommand.terminal.TerminalCommand;

public class RemoveCommand extends AbstractCommand{

    private TerminalCommand del;
    private ObjID objID;

    RemoveCommand(TerminalCommand del,ObjID objID){
        this.del = del;
        this.objID = objID;
    }
}
