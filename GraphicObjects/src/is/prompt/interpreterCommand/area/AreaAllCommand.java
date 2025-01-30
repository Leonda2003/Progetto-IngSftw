package is.prompt.interpreterCommand.area;

import is.prompt.interpreterCommand.terminal.TerminalCommand;

public class AreaAllCommand extends AreaCommand{

    TerminalCommand all;

    public AreaAllCommand(TerminalCommand area, TerminalCommand all) {
        this.area = area;
        this.all = all;
    }

    @Override
    public String toString() {
        return "AreaCommand: "+ area + all;
    }


}
