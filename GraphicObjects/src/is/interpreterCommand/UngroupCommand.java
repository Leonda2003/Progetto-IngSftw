package is.interpreterCommand;

import is.interpreterCommand.terminal.TerminalCommand;

public class UngroupCommand extends AbstractCommand{

    private TerminalCommand ungrp;
    private ListIDCommand listIDCommand;

    UngroupCommand(TerminalCommand ungrp, ListIDCommand listIDCommand){
        this.ungrp = ungrp;
        this.listIDCommand = listIDCommand;
    }
}
