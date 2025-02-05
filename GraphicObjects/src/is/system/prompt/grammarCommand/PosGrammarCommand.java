package is.system.prompt.grammarCommand;

import is.system.prompt.grammarCommand.terminal.Posfloat;
import is.system.prompt.visitor.Visitor;

public class PosGrammarCommand extends AbstractGrammarCommand {

    private Posfloat posfloat1;
    private Posfloat posfloat2;

    public PosGrammarCommand(Posfloat posfloat1, Posfloat posfloat2) {
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
