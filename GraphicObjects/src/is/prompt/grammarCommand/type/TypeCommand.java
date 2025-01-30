package is.prompt.grammarCommand.type;

import is.prompt.parser.analyzer.Token;
import is.prompt.grammarCommand.AbstractCommand;

public abstract class TypeCommand extends AbstractCommand {

    Token shape;

    TypeCommand(Token token){
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
