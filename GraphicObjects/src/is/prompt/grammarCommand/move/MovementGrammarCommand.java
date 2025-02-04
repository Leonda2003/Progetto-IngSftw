package is.prompt.grammarCommand.move;

import is.prompt.grammarCommand.AbstractGrammarCommand;
import is.prompt.grammarCommand.PosGrammarCommand;
import is.prompt.grammarCommand.terminal.ObjID;

public abstract class MovementGrammarCommand extends AbstractGrammarCommand {

    ObjID objID;
    PosGrammarCommand pos;


    public ObjID getObjID() {
        return objID;
    }

    public PosGrammarCommand getPos() {
        return pos;
    }
}
