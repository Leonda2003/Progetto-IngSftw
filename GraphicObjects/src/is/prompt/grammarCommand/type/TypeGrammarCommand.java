package is.prompt.grammarCommand.type;

import is.prompt.parser.analyzer.Token;
import is.prompt.grammarCommand.AbstractGrammarCommand;

public abstract class TypeGrammarCommand extends AbstractGrammarCommand {

    Token shape;

    TypeGrammarCommand(Token token){
        this.shape = token;
    }



    @Override
    public String toString() {
        return shape.toString()+" ";
    }

    public Token getToken() {
        return shape;
    }
}
