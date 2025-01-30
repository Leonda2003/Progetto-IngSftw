package is.prompt.grammarCommand;

import is.prompt.grammarCommand.terminal.Posfloat;
import is.prompt.visitor.Visitor;

public class PosCommand extends AbstractCommand{

    private Posfloat posfloat1;
    private Posfloat posfloat2;

    public PosCommand(Posfloat posfloat1, Posfloat posfloat2) {
        this.posfloat1 = posfloat1;
        this.posfloat2 = posfloat2;
    }

    public void accept(Visitor v){
        v.interpret(this);
    }

    @Override
    public String toString() {
        return "("+posfloat1+","+posfloat2+")" ;
    }

    public Posfloat getPosfloat1() {
        return posfloat1;
    }

    public Posfloat getPosfloat2() {
        return posfloat2;
    }
}
