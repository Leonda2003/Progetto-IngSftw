package is.interpreterCommand.terminal;

import is.analyzer.Token;
import is.interpreterCommand.AbstractCommand;
import is.visitor.Visitor;

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
