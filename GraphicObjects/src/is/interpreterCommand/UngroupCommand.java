package is.interpreterCommand;

import is.interpreterCommand.terminal.ObjID;
import is.interpreterCommand.terminal.TerminalCommand;
import is.visitor.Visitor;

public class UngroupCommand extends AbstractCommand{

    private TerminalCommand ungrp;
    private ObjID objID;

    public UngroupCommand(TerminalCommand ungrp, ObjID objID){
        this.ungrp = ungrp;
        this.objID = objID;
    }

    public String accept(Visitor v){
        v.interpret(this);
        return null;
    }

    @Override
    public String toString() {
        return "UngroupCommand: "+ ungrp + objID;
    }
}
