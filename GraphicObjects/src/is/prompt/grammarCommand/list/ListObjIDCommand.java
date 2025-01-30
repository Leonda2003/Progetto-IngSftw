package is.prompt.grammarCommand.list;

import is.prompt.grammarCommand.terminal.ObjID;
import is.prompt.grammarCommand.terminal.TerminalCommand;
import is.prompt.visitor.Visitor;

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
