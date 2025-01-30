package is.prompt.grammarCommand;

import is.prompt.grammarCommand.terminal.ObjID;
import is.prompt.grammarCommand.terminal.TerminalCommand;
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
