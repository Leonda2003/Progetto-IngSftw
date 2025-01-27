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

    public void accept(Visitor v){v.interpret(this);}



}
