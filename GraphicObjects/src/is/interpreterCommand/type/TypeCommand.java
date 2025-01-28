package is.interpreterCommand.type;

import is.analyzer.Token;
import is.interpreterCommand.AbstractCommand;
import is.visitor.Visitor;

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
