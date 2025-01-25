package is.interpreterCommand.typeconstr;

import is.interpreterCommand.AbstractCommand;
import is.visitor.Visitor;

public abstract class TypeconstrCommand extends AbstractCommand {

    public void accept(Visitor v){
        v.interpret(this);
    }
}
