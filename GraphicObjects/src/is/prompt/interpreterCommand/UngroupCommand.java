package is.prompt.interpreterCommand;

import is.prompt.interpreterCommand.terminal.ObjID;
import is.prompt.interpreterCommand.terminal.TerminalCommand;
import is.prompt.visitor.Visitor;

public class UngroupCommand extends AbstractCommand{

    private TerminalCommand ungrp;
    private ObjID objID;

    public UngroupCommand(TerminalCommand ungrp, ObjID objID){
        this.ungrp = ungrp;
        this.objID = objID;
    }

    public void accept(Visitor v){
        v.interpret(this);
    }

    @Override
    public String toString() {
        return "UngroupCommand: "+ ungrp + objID;
    }
}
