package is.system.prompt.grammarCommand.list;

import is.system.prompt.grammarCommand.terminal.All_Groups;
import is.system.prompt.grammarCommand.terminal.TerminalGrammarCommand;
import is.system.prompt.visitor.Visitor;

import java.lang.reflect.InvocationTargetException;

public class ListGroupsGrammarCommand extends ListGrammarCommand {

    All_Groups groups;

    public ListGroupsGrammarCommand(TerminalGrammarCommand ls, All_Groups groups) {
        this.ls = ls;
        this.groups = groups;
    }

    @Override
    public String toString() {
        return "ListCommand: "+ ls + groups;
    }


    public All_Groups getGroups() {
        return groups;
    }

    @Override
    public void accept(Visitor v) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        v.interpret(this);
    }
}
