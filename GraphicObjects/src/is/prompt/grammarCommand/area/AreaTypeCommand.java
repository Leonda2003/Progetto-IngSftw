package is.prompt.grammarCommand.area;

import is.prompt.grammarCommand.terminal.TerminalCommand;
import is.prompt.grammarCommand.type.TypeCommand;

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
