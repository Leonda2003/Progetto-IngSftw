package is.prompt.grammarCommand.terminal;

import is.prompt.parser.analyzer.Token;
import is.prompt.grammarCommand.AbstractCommand;

public class TerminalCommand extends AbstractCommand {

    Token token;

    public TerminalCommand(Token token){
        this.token = token;
    }

    public Token getToken() {
        return token;
    }


    @Override
    public String toString() {
        return token.toString()+" ";
    }
}
