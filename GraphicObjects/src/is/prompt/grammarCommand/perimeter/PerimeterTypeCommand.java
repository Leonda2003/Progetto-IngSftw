package is.prompt.grammarCommand.perimeter;

import is.prompt.grammarCommand.terminal.TerminalCommand;
import is.prompt.grammarCommand.type.TypeCommand;

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
