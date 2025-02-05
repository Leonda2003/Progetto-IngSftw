package is.system.prompt.grammarCommand.move;

import is.system.prompt.grammarCommand.AbstractGrammarCommand;
import is.system.prompt.grammarCommand.PosGrammarCommand;
import is.system.prompt.grammarCommand.terminal.ObjID;

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
