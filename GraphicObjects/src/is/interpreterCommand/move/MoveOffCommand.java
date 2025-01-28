package is.interpreterCommand.move;

import is.interpreterCommand.PosCommand;
import is.interpreterCommand.terminal.ObjID;
import is.interpreterCommand.terminal.TerminalCommand;
import is.visitor.Visitor;

import java.lang.reflect.InvocationTargetException;

public class MoveOffCommand extends MovementCommand{

    TerminalCommand mv;

    public MoveOffCommand(TerminalCommand mv,  ObjID objID, PosCommand pos){
        this.mv = mv;
        super.objID = objID;
        super.pos = pos;
    }

    @Override
    public String toString() {
        return "MovementCommand: "+ mv + objID + pos;
    }


}
