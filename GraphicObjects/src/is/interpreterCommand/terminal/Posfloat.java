package is.interpreterCommand.terminal;

import is.analyzer.Token;

public class Posfloat extends TerminalCommand{

    float posfloat;
    public Posfloat(Token token, double posfloat) {
        super(token);
        if(posfloat > Float.MAX_VALUE) throw new IllegalArgumentException("try a smaller position value");
        this.posfloat = (float) posfloat;
    }
}
