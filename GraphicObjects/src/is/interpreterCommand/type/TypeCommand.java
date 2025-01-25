package is.interpreterCommand.type;

import is.interpreterCommand.AbstractCommand;
import is.visitor.Visitor;

public abstract class TypeCommand extends AbstractCommand {

    public void accept(Visitor v){
        v.interpret(this);
    }
}
