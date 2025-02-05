package is.system.prompt.grammarCommand.list;

import is.system.prompt.grammarCommand.terminal.ObjID;
import is.system.prompt.grammarCommand.terminal.TerminalGrammarCommand;
import is.system.prompt.visitor.Visitor;

import java.lang.reflect.InvocationTargetException;

public class ListObjIDGrammarCommand extends ListGrammarCommand {

    ObjID objID;

    public ListObjIDGrammarCommand(TerminalGrammarCommand ls, ObjID objID) {
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
