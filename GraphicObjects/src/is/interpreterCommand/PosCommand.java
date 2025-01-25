package is.interpreterCommand;

import is.interpreterCommand.terminal.Posfloat;
import is.interpreterCommand.terminal.TerminalCommand;
import is.visitor.Visitor;

public class PosCommand extends AbstractCommand{

    Posfloat posfloat1;
    Posfloat posfloat2;

    public PosCommand(Posfloat posfloat1, Posfloat posfloat2) {
        this.posfloat1 = posfloat1;
        this.posfloat2 = posfloat2;
    }

    public void accept(Visitor v){
        v.interpret(this);
    }
}
