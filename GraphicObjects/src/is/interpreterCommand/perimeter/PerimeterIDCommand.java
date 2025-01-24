package is.interpreterCommand.perimeter;

import is.interpreterCommand.terminal.ObjID;
import is.interpreterCommand.terminal.TerminalCommand;

public class PerimeterIDCommand extends PerimeterCommand{

    TerminalCommand perimeter;
    ObjID objID;

    public PerimeterIDCommand(TerminalCommand perimeter, ObjID objID) {
        this.perimeter = perimeter;
        this.objID = objID;
    }
}
