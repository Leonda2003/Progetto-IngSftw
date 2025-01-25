package is.interpreterCommand.perimeter;

import is.interpreterCommand.terminal.TerminalCommand;

public class PerimeterAllCommand extends PerimeterCommand{

    TerminalCommand all;

    public PerimeterAllCommand(TerminalCommand perimeter, TerminalCommand all) {
        this.perimeter = perimeter;
        this.all = all;
    }
}
