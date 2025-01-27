package is.interpreterCommand.area;

import is.interpreterCommand.terminal.TerminalCommand;
import is.interpreterCommand.type.TypeCommand;

public class AreaTypeCommand extends AreaCommand{


    TypeCommand type;

    public AreaTypeCommand(TerminalCommand area, TypeCommand type) {
        this.area = area;
        this.type = type;
    }

    @Override
    public String toString() {
        return "AreaCommand: "+ area + type;
    }
}
