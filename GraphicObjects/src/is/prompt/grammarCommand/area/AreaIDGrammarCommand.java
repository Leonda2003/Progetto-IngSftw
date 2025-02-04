package is.prompt.grammarCommand.area;

import is.prompt.grammarCommand.terminal.ObjID;
import is.prompt.grammarCommand.terminal.TerminalGrammarCommand;
import is.prompt.visitor.Visitor;

import java.lang.reflect.InvocationTargetException;

public class AreaIDGrammarCommand extends AreaGrammarCommand {

    ObjID objID;

    public AreaIDGrammarCommand(TerminalGrammarCommand area, ObjID objID) {
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
