package is.interpreterCommand;

import is.interpreterCommand.terminal.TerminalCommand;
import is.visitor.Visitor;

public class UngroupCommand extends AbstractCommand{

    private TerminalCommand ungrp;
    private ListIDCommand listIDCommand;

    UngroupCommand(TerminalCommand ungrp, ListIDCommand listIDCommand){
        this.ungrp = ungrp;
        this.listIDCommand = listIDCommand;
    }

    public void accept(Visitor v){
        v.interpret(this);
    }
}
