package is.interpreterCommand;

import is.interpreterCommand.terminal.ObjID;
import is.interpreterCommand.terminal.TerminalCommand;
import is.visitor.Visitor;

import java.util.LinkedList;
import java.util.List;

public class ListIDCommand extends AbstractCommand{

    List<ObjID> listObjID = new LinkedList<>();

    public void accept(Visitor v){
        v.interpret(this);
    }

}
