package is.interpreterCommand;

import is.interpreterCommand.terminal.TerminalCommand;

public class GroupCommand extends AbstractCommand{


    private TerminalCommand grp;
    private ListIDCommand listIDCommand;

    GroupCommand(TerminalCommand grp, ListIDCommand listIDCommand){
        this.grp = grp;
        this.listIDCommand = listIDCommand;
    }
}
