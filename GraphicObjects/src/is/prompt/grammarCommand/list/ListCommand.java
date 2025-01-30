package is.prompt.grammarCommand.list;

import is.prompt.grammarCommand.AbstractCommand;
import is.prompt.grammarCommand.terminal.TerminalCommand;

public abstract class ListCommand extends AbstractCommand {

    TerminalCommand ls;


    public TerminalCommand getLs() {
        return ls;
    }
}