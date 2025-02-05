package is.system.prompt.grammarCommand.list;

import is.system.prompt.grammarCommand.terminal.All_Groups;
import is.system.prompt.grammarCommand.terminal.TerminalGrammarCommand;
import is.system.prompt.visitor.Visitor;

import java.lang.reflect.InvocationTargetException;

public class ListAllGrammarCommand extends ListGrammarCommand {
    All_Groups all;

    public ListAllGrammarCommand(TerminalGrammarCommand ls, All_Groups all) {
        this.ls = ls;
        this.all = all;
    }

    @Override
    public String toString() {
        return "ListCommand: "+ ls + all;
    }


    public All_Groups getAll() {
        return all;
    }

    @Override
    public void accept(Visitor v) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        v.interpret(this);
    }
}
