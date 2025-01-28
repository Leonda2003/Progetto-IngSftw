package is.interpreterCommand.terminal;

import is.analyzer.Token;

public class Path extends TerminalCommand{

    String path;
    public Path(Token token,String path) {
        super(token);
        this.path = path;
    }

    @Override
    public String toString() {
        return path+" ";
    }

    public String getPath() {
        return path;
    }
}
