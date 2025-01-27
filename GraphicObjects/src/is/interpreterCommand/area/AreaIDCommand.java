package is.interpreterCommand.area;

import is.interpreterCommand.terminal.ObjID;
import is.interpreterCommand.terminal.TerminalCommand;

public class AreaIDCommand extends AreaCommand{

    ObjID objID;

    public AreaIDCommand(TerminalCommand area, ObjID objID) {
        this.area = area;
        this.objID = objID;
    }

    @Override
    public String toString() {
        return "AreaCommand: "+ area + objID;
    }
}
