package is.interpreterCommand.list;

import is.interpreterCommand.terminal.TerminalCommand;

public class ListGroupsCommand extends ListCommand{

    TerminalCommand groups;

    public ListGroupsCommand(TerminalCommand ls, TerminalCommand groups) {
        this.ls = ls;
        this.groups = groups;
    }

    @Override
    public String toString() {
        return "ListCommand: "+ ls + groups;
    }
}
