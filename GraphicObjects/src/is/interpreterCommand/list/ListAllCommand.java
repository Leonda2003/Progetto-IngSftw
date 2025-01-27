package is.interpreterCommand.list;

import is.interpreterCommand.terminal.TerminalCommand;

public class ListAllCommand extends ListCommand{
    TerminalCommand all;

    public ListAllCommand(TerminalCommand ls, TerminalCommand all) {
        this.ls = ls;
        this.all = all;
    }

    @Override
    public String toString() {
        return "ListCommand: "+ ls + all;
    }
}
