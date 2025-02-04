package is.prompt.grammarCommand.terminal;

import is.prompt.parser.analyzer.Token;
import is.prompt.grammarCommand.AbstractGrammarCommand;

public class TerminalGrammarCommand extends AbstractGrammarCommand {

    Token token;

    public TerminalGrammarCommand(Token token){
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
