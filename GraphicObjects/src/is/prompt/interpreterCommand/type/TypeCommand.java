package is.prompt.interpreterCommand.type;

import is.prompt.parser.analyzer.Token;
import is.prompt.interpreterCommand.AbstractCommand;

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
