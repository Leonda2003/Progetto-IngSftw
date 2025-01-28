package is.interpreterCommand.typeconstr;

import is.interpreterCommand.terminal.Posfloat;
import is.interpreterCommand.terminal.TerminalCommand;
import is.interpreterCommand.type.TypeCommand;
import is.visitor.Visitor;

public class CircleCommand extends TypeconstrCommand{

    Posfloat posfloat;

    public CircleCommand(TypeCommand cirle, Posfloat posfloat) {
        this.shape = cirle;
        this.posfloat = posfloat;
    }

    @Override
    public String toString() {
        return shape+"("+ posfloat +")"+" ";
    }



    public Posfloat getPosfloat() {
        return posfloat;
    }
}
