package is.interpreterCommand.move;

import is.interpreterCommand.PosCommand;
import is.interpreterCommand.terminal.ObjID;
import is.interpreterCommand.terminal.TerminalCommand;
import is.visitor.Visitor;

import java.lang.reflect.InvocationTargetException;

public class MoveCommand extends MovementCommand{

    TerminalCommand mv;

    public MoveCommand(TerminalCommand mv,ObjID objID,PosCommand pos){
        this.mv = mv;
        super.objID = objID;
        super.pos = pos;
    }

    @Override
    public String toString() {
        return "MoveCommand: "+ mv + objID + pos;
    }


    public TerminalCommand getMv() {
        return mv;
    }

    @Override
    public void accept(Visitor v) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        v.interpret(this);
    }
}
