package is.system.prompt.grammarCommand.terminal;

import is.system.prompt.parser.analyzer.Token;

public class Posfloat extends TerminalGrammarCommand {

    private float posfloat;
    public Posfloat(Token token, double posfloat) {
        super(token);
        if(posfloat > Float.MAX_VALUE) throw new IllegalArgumentException("try a smaller position value");
        this.posfloat = (float) posfloat;
    }

    @Override
    public String toString() {
        return " "+posfloat+" ";
    }

    public float getPosfloat() {
        return posfloat;
    }
}
