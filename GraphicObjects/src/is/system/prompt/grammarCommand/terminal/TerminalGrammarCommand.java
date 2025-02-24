package is.system.prompt.grammarCommand.terminal;

import is.system.prompt.grammarCommand.GrammarCommand;
import is.system.prompt.parser.analyzer.Token;
import is.system.prompt.visitor.Visitor;

import java.lang.reflect.InvocationTargetException;


public class TerminalGrammarCommand implements GrammarCommand {

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

    @Override
    public void accept(Visitor v) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        v.interpret(this);
    }
}
