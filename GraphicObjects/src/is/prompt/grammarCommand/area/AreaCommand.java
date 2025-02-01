package is.prompt.grammarCommand.area;

import is.prompt.grammarCommand.AbstractCommand;
import is.prompt.grammarCommand.terminal.TerminalCommand;

public abstract class AreaCommand extends AbstractCommand {

    TerminalCommand area;

    public TerminalCommand getArea() {
        return area;
    }
}
