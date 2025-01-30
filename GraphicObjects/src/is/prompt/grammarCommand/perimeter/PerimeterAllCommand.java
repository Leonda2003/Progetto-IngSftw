package is.prompt.grammarCommand.perimeter;

import is.prompt.grammarCommand.terminal.TerminalCommand;

public class PerimeterAllCommand extends PerimeterCommand{

    TerminalCommand all;

    public PerimeterAllCommand(TerminalCommand perimeter, TerminalCommand all) {
        this.perimeter = perimeter;
        this.all = all;
    }

    @Override
    public String toString() {
        return "PerimeterCommand: "+perimeter + all;
    }


}
