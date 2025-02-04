package is.prompt.grammarCommand.move;

import is.prompt.grammarCommand.PosGrammarCommand;
import is.prompt.grammarCommand.terminal.ObjID;
import is.prompt.grammarCommand.terminal.TerminalGrammarCommand;
import is.prompt.visitor.Visitor;

import java.lang.reflect.InvocationTargetException;

public class MoveOffGrammarCommand extends MovementGrammarCommand {

    TerminalGrammarCommand mvoff;

    public MoveOffGrammarCommand(TerminalGrammarCommand mvoff, ObjID objID, PosGrammarCommand pos){
        this.mvoff = mvoff;
        super.objID = objID;
        super.pos = pos;
    }
    @Override
    public String toString() {
        return "MoveOffCommand: "+ mvoff + objID + pos;
    }

    public TerminalGrammarCommand getMvoff() {
        return mvoff;
    }

    @Override
    public void accept(Visitor v) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        v.interpret(this);
    }
}
