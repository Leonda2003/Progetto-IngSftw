package is.prompt.interpreterCommand.area;

import is.prompt.interpreterCommand.terminal.TerminalCommand;
import is.prompt.interpreterCommand.type.TypeCommand;

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
