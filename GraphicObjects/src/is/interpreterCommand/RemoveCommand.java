package is.interpreterCommand;

import is.interpreterCommand.terminal.ObjID;
import is.interpreterCommand.terminal.TerminalCommand;
import is.visitor.Visitor;

public class RemoveCommand extends AbstractCommand{

    private TerminalCommand del;
    private ObjID objID;

    public RemoveCommand(TerminalCommand del, ObjID objID){
        this.del = del;
        this.objID = objID;
    }

    public String accept(Visitor v){
        v.interpret(this);
        return null;
    }

    @Override
    public String toString() {
        return "RemoveCommand: " +del + objID;
    }
}
