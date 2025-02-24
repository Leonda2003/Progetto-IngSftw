package is.system.prompt.grammarCommand.move;


import is.system.prompt.grammarCommand.GrammarCommand;
import is.system.prompt.grammarCommand.PosGrammarCommand;
import is.system.prompt.grammarCommand.terminal.ObjID;

public abstract class MovementGrammarCommand implements GrammarCommand {

    ObjID objID;
    PosGrammarCommand pos;


    public ObjID getObjID() {
        return objID;
    }

    public PosGrammarCommand getPos() {
        return pos;
    }
}
