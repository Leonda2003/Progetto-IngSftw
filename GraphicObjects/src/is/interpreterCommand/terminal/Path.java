package is.interpreterCommand.terminal;

import is.analyzer.Token;

public class Path extends TerminalCommand{

    String path;
    public Path(Token token,String path) {
        super(token);
        this.path = path;
    }
}
