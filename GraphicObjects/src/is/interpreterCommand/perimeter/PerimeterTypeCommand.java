package is.interpreterCommand.perimeter;

import is.interpreterCommand.terminal.TerminalCommand;
import is.interpreterCommand.type.TypeCommand;

public class PerimeterTypeCommand extends PerimeterCommand{

    TerminalCommand perimeter;
    TypeCommand type;

    public PerimeterTypeCommand(TerminalCommand perimeter, TypeCommand type) {
        this.perimeter = perimeter;
        this.type = type;
    }
}
