package is.prompt.interpreterCommand.list;

import is.prompt.interpreterCommand.AbstractCommand;
import is.prompt.interpreterCommand.terminal.TerminalCommand;

public abstract class ListCommand extends AbstractCommand {

    TerminalCommand ls;


    public TerminalCommand getLs() {
        return ls;
    }
}