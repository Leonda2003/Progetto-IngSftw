package is.prompt.interpreterCommand.typeconstr;

import is.prompt.interpreterCommand.terminal.Posfloat;
import is.prompt.interpreterCommand.type.TypeCommand;

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
