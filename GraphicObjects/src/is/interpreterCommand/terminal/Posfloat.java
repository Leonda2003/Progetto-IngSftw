package is.interpreterCommand.terminal;

import is.analyzer.Token;

public class Posfloat extends TerminalCommand{

    float posfloat;
    Posfloat(Token token, float posfloat) {
        super(token);
        this.posfloat = posfloat;
    }
}
