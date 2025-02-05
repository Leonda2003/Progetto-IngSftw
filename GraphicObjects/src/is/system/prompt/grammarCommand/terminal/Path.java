package is.system.prompt.grammarCommand.terminal;

import is.system.prompt.parser.analyzer.Token;

public class Path extends TerminalGrammarCommand {

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
