package is.interpreterCommand.list;

import is.interpreterCommand.terminal.TerminalCommand;
import is.interpreterCommand.type.TypeCommand;

public class ListTypeCommand extends ListCommand{

    TypeCommand typeCommand;

    public ListTypeCommand(TerminalCommand ls, TypeCommand typeCommand) {
        this.ls = ls;
        this.typeCommand = typeCommand;
    }
}
