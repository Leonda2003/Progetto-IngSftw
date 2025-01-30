package is.prompt.grammarCommand.terminal;

import is.prompt.parser.analyzer.Token;

public class Posfloat extends TerminalCommand{

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
