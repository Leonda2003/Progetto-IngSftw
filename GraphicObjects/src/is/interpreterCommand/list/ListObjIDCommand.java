package is.interpreterCommand.list;

import is.interpreterCommand.terminal.ObjID;
import is.interpreterCommand.terminal.TerminalCommand;

public class ListObjIDCommand extends ListCommand{

    TerminalCommand ls;
    ObjID objID;

    public ListObjIDCommand(TerminalCommand ls, ObjID objID) {
        this.ls = ls;
        this.objID = objID;
    }
}
