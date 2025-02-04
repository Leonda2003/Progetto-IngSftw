package is.prompt.grammarCommand.typeconstr;

import is.prompt.grammarCommand.terminal.Posfloat;
import is.prompt.grammarCommand.type.TypeGrammarCommand;

public class CircleGrammarCommand extends TypeconstrGrammarCommand {

    Posfloat posfloat;

    public CircleGrammarCommand(TypeGrammarCommand cirle, Posfloat posfloat) {
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
