package is.interpreterCommand.list;

import is.interpreterCommand.AbstractCommand;
import is.interpreterCommand.terminal.TerminalCommand;
import is.visitor.Visitor;

public abstract class ListCommand extends AbstractCommand {

    TerminalCommand ls;


    public TerminalCommand getLs() {
        return ls;
    }
}