package is.interpreterCommand.typeconstr;

import is.interpreterCommand.terminal.Posfloat;
import is.interpreterCommand.terminal.TerminalCommand;
import is.interpreterCommand.type.TypeCommand;

public class CircleCommand extends TypeconstrCommand{

    TypeCommand cirle;
    Posfloat posfloat;

    public CircleCommand(TypeCommand cirle, Posfloat posfloat) {
        this.cirle = cirle;
        this.posfloat = posfloat;
    }

    @Override
    public String toString() {
        return "TypeconstrCommand: "+ cirle+"("+ posfloat +")";
    }
}
