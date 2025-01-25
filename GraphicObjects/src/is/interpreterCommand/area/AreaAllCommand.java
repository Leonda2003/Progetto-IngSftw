package is.interpreterCommand.area;

import is.interpreterCommand.terminal.TerminalCommand;

public class AreaAllCommand extends AreaCommand{

    TerminalCommand all;

    public AreaAllCommand(TerminalCommand area, TerminalCommand all) {
        this.area = area;
        this.all = all;
    }
}
