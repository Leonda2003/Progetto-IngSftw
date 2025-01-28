package is.interpreterCommand.list;

import is.interpreterCommand.terminal.ObjID;
import is.interpreterCommand.terminal.TerminalCommand;
import is.visitor.Visitor;

import java.lang.reflect.InvocationTargetException;

public class ListObjIDCommand extends ListCommand{

    ObjID objID;

    public ListObjIDCommand(TerminalCommand ls, ObjID objID) {
        this.ls = ls;
        this.objID = objID;
    }

    @Override
    public String toString() {
        return "ListCommand: "+ ls + objID;
    }


}
