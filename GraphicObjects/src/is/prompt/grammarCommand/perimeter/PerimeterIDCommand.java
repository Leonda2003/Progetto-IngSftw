package is.prompt.grammarCommand.perimeter;

import is.prompt.grammarCommand.terminal.ObjID;
import is.prompt.grammarCommand.terminal.TerminalCommand;

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
