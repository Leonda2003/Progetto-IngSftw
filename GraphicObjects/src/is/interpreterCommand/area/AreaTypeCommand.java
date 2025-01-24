package is.interpreterCommand.area;

import is.interpreterCommand.terminal.TerminalCommand;
import is.interpreterCommand.type.TypeCommand;

public class AreaTypeCommand extends AreaCommand{

    TerminalCommand area;
    TypeCommand type;

    public AreaTypeCommand(TerminalCommand area, TypeCommand type) {
        this.area = area;
        this.type = type;
    }
}
