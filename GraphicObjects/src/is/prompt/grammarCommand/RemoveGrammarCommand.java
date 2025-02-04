package is.prompt.grammarCommand;

import is.prompt.grammarCommand.terminal.ObjID;
import is.prompt.grammarCommand.terminal.TerminalGrammarCommand;
import is.prompt.visitor.Visitor;

import java.lang.reflect.InvocationTargetException;

public class RemoveGrammarCommand extends AbstractGrammarCommand {

    private TerminalGrammarCommand del;
    private ObjID objID;

    public RemoveGrammarCommand(TerminalGrammarCommand del, ObjID objID){
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

    public TerminalGrammarCommand getDel() {
        return del;
    }

    public ObjID getObjID() {
        return objID;
    }
}
