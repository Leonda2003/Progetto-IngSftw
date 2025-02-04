package is.prompt.grammarCommand;

import is.prompt.grammarCommand.terminal.ObjID;
import is.prompt.grammarCommand.terminal.Posfloat;
import is.prompt.grammarCommand.terminal.TerminalGrammarCommand;
import is.prompt.visitor.Visitor;

import java.lang.reflect.InvocationTargetException;

public class ScaleGrammarCommand extends AbstractGrammarCommand {

    TerminalGrammarCommand scale;
    ObjID objID;
    Posfloat posfloat;

    public ScaleGrammarCommand(TerminalGrammarCommand scale, ObjID objID, Posfloat posfloat) {
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

    public TerminalGrammarCommand getScale() {
        return scale;
    }

    public ObjID getObjID() {
        return objID;
    }

    public Posfloat getPosfloat() {
        return posfloat;
    }
}
