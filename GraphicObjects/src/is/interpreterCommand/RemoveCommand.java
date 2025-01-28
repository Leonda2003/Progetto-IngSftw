package is.interpreterCommand;

import is.interpreterCommand.terminal.ObjID;
import is.interpreterCommand.terminal.TerminalCommand;
import is.visitor.Visitor;

import java.lang.reflect.InvocationTargetException;

public class RemoveCommand extends AbstractCommand{

    private TerminalCommand del;
    private ObjID objID;

    public RemoveCommand(TerminalCommand del, ObjID objID){
        this.del = del;
        this.objID = objID;
    }

    public String accept(Visitor v) throws InvocationTargetException, InstantiationException, IllegalAccessException {
        return v.interpret(this);
    }

    @Override
    public String toString() {
        return "RemoveCommand: " +del + objID;
    }

    public TerminalCommand getDel() {
        return del;
    }

    public ObjID getObjID() {
        return objID;
    }
}
