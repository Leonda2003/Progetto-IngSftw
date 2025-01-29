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

    public ObjID getObjID() {
        return objID;
    }

    @Override
    public String toString() {
        return "ListCommand: "+ ls + objID;
    }

    @Override
    public void accept(Visitor v) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        v.interpret(this);
    }


}
