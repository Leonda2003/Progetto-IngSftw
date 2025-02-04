package is.prompt.grammarCommand;

import is.prompt.grammarCommand.terminal.ObjID;
import is.prompt.grammarCommand.terminal.TerminalGrammarCommand;
import is.prompt.visitor.Visitor;

import java.lang.reflect.InvocationTargetException;

public class UngroupGrammarCommand extends AbstractGrammarCommand {

    private TerminalGrammarCommand ungrp;
    private ObjID objID;

    public UngroupGrammarCommand(TerminalGrammarCommand ungrp, ObjID objID){
        this.ungrp = ungrp;
        this.objID = objID;
    }

    public void accept(Visitor v) throws InvocationTargetException, InstantiationException, IllegalAccessException {
        v.interpret(this);
    }

    @Override
    public String toString() {
        return "UngroupCommand: "+ ungrp + objID;
    }

    public TerminalGrammarCommand getUngrp() {
        return ungrp;
    }

    public ObjID getObjID() {
        return objID;
    }
}
