package is.system.prompt.grammarCommand.move;

import is.system.prompt.grammarCommand.PosGrammarCommand;
import is.system.prompt.grammarCommand.terminal.ObjID;
import is.system.prompt.grammarCommand.terminal.TerminalGrammarCommand;
import is.system.prompt.visitor.Visitor;

import java.lang.reflect.InvocationTargetException;

public class MoveGrammarCommand extends MovementGrammarCommand {

    TerminalGrammarCommand mv;

    public MoveGrammarCommand(TerminalGrammarCommand mv, ObjID objID, PosGrammarCommand pos){
        this.mv = mv;
        super.objID = objID;
        super.pos = pos;
    }

    @Override
    public String toString() {
        return "MoveCommand: "+ mv + objID + pos;
    }


    public TerminalGrammarCommand getMv() {
        return mv;
    }

    @Override
    public void accept(Visitor v) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        v.interpret(this);
    }
}
