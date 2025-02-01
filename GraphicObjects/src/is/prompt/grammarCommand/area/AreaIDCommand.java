package is.prompt.grammarCommand.area;

import is.prompt.grammarCommand.terminal.ObjID;
import is.prompt.grammarCommand.terminal.TerminalCommand;

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


    public ObjID getObjID() {
        return objID;
    }
}
