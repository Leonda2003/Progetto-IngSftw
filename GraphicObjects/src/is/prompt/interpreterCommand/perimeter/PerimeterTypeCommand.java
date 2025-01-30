package is.prompt.interpreterCommand.perimeter;

import is.prompt.interpreterCommand.terminal.TerminalCommand;
import is.prompt.interpreterCommand.type.TypeCommand;

public class PerimeterTypeCommand extends PerimeterCommand{

    TypeCommand type;

    public PerimeterTypeCommand(TerminalCommand perimeter, TypeCommand type) {
        this.perimeter = perimeter;
        this.type = type;
    }

    @Override
    public String toString() {
        return "PerimeterCommand: "+perimeter + type;
    }


}
