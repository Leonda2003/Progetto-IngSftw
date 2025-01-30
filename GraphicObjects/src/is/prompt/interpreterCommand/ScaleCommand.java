package is.prompt.interpreterCommand;

import is.prompt.interpreterCommand.terminal.ObjID;
import is.prompt.interpreterCommand.terminal.Posfloat;
import is.prompt.interpreterCommand.terminal.TerminalCommand;
import is.prompt.visitor.Visitor;

import java.lang.reflect.InvocationTargetException;

public class ScaleCommand extends AbstractCommand{

    TerminalCommand scale;
    ObjID objID;
    Posfloat posfloat;

    public ScaleCommand(TerminalCommand scale, ObjID objID, Posfloat posfloat) {
        this.scale = scale;
        this.objID = objID;
        this.posfloat = posfloat;
    }

    public void accept(Visitor v) throws InvocationTargetException, InstantiationException, IllegalAccessException {
        v.interpret(this);
    }

    @Override
    public String toString() {
        return "ScaleCommand: "+ scale + objID + posfloat;
    }

    public TerminalCommand getScale() {
        return scale;
    }

    public ObjID getObjID() {
        return objID;
    }

    public Posfloat getPosfloat() {
        return posfloat;
    }
}
