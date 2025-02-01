package is.prompt.grammarCommand.area;

import is.prompt.grammarCommand.terminal.ObjID;
import is.prompt.grammarCommand.terminal.TerminalCommand;
import is.prompt.visitor.Visitor;

import java.lang.reflect.InvocationTargetException;

public class AreaIDCommand extends AreaCommand{

    ObjID objID;

    public AreaIDCommand(TerminalCommand area, ObjID objID) {
        this.area = area;
        this.objID = objID;
    }

    @Override
    public String toString() {
        return "AreaCommand: "+ area + objID;
    }


    @Override
    public void accept(Visitor v) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        v.interpret(this);
    }

    public ObjID getObjID() {
        return objID;
    }
}
