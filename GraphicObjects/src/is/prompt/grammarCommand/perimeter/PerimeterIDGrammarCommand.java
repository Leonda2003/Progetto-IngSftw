package is.prompt.grammarCommand.perimeter;

import is.prompt.grammarCommand.terminal.ObjID;
import is.prompt.grammarCommand.terminal.TerminalGrammarCommand;
import is.prompt.visitor.Visitor;

import java.lang.reflect.InvocationTargetException;

public class PerimeterIDGrammarCommand extends PerimeterGrammarCommand {

    ObjID objID;

    public PerimeterIDGrammarCommand(TerminalGrammarCommand perimeter, ObjID objID) {
        this.perimeter = perimeter;
        this.objID = objID;
    }

    @Override
    public String toString() {
        return "PerimeterCommand: "+perimeter + objID;
    }

    @Override
    public void accept(Visitor v) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        v.interpret(this);
    }

    public ObjID getObjID() {
        return objID;
    }
}
