package is.prompt.grammarCommand.move;

import is.prompt.grammarCommand.PosCommand;
import is.prompt.grammarCommand.terminal.ObjID;
import is.prompt.grammarCommand.terminal.TerminalCommand;
import is.prompt.visitor.Visitor;

import java.lang.reflect.InvocationTargetException;

public class MoveOffCommand extends MovementCommand{

    TerminalCommand mvoff;

    public MoveOffCommand(TerminalCommand mvoff,  ObjID objID, PosCommand pos){
        this.mvoff = mvoff;
        super.objID = objID;
        super.pos = pos;
    }
    @Override
    public String toString() {
        return "MoveOffCommand: "+ mvoff + objID + pos;
    }

    public TerminalCommand getMvoff() {
        return mvoff;
    }

    @Override
    public void accept(Visitor v) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        v.interpret(this);
    }
}
