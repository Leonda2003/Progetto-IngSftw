package is.interpreterCommand.typeconstr;

import is.interpreterCommand.terminal.Posfloat;
import is.interpreterCommand.terminal.TerminalCommand;
import is.interpreterCommand.type.TypeCommand;
import is.visitor.Visitor;

public class CircleCommand extends TypeconstrCommand{

    TypeCommand cirle;
    Posfloat posfloat;

    public CircleCommand(TypeCommand cirle, Posfloat posfloat) {
        this.cirle = cirle;
        this.posfloat = posfloat;
    }

    @Override
    public String toString() {
        return cirle+"("+ posfloat +")"+" ";
    }

    public void accept(Visitor v){
        v.interpret(this);
    }

    public TypeCommand getCirle() {
        return cirle;
    }

    public Posfloat getPosfloat() {
        return posfloat;
    }
}
