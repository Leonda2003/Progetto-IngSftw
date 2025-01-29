package is.interpreterCommand.list;

import is.interpreterCommand.terminal.All_Groups;
import is.interpreterCommand.terminal.TerminalCommand;
import is.visitor.Visitor;

import java.lang.reflect.InvocationTargetException;

public class ListGroupsCommand extends ListCommand{

    All_Groups groups;

    public ListGroupsCommand(TerminalCommand ls, All_Groups groups) {
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
