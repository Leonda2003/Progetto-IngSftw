package is.prompt.interpreterCommand.perimeter;

import is.prompt.interpreterCommand.terminal.ObjID;
import is.prompt.interpreterCommand.terminal.TerminalCommand;

public class PerimeterIDCommand extends PerimeterCommand{

    ObjID objID;

    public PerimeterIDCommand(TerminalCommand perimeter, ObjID objID) {
        this.perimeter = perimeter;
        this.objID = objID;
    }

    @Override
    public String toString() {
        return "PerimeterCommand: "+perimeter + objID;
    }




}
