package is.system.prompt.grammarCommand.type;

import is.system.prompt.parser.analyzer.Token;
import is.system.prompt.grammarCommand.AbstractGrammarCommand;

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
