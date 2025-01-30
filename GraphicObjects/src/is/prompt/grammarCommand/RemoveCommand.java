package is.prompt.grammarCommand;

import is.prompt.grammarCommand.terminal.ObjID;
import is.prompt.grammarCommand.terminal.TerminalCommand;
import is.prompt.visitor.Visitor;

import java.lang.reflect.InvocationTargetException;

public class RemoveCommand extends AbstractCommand{

    private TerminalCommand del;
    private ObjID objID;

    public RemoveCommand(TerminalCommand del, ObjID objID){
        this.del = del;
        this.objID = objID;
    }

    public void accept(Visitor v) throws InvocationTargetException, InstantiationException, IllegalAccessException {
        v.interpret(this);
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
