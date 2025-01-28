package is.interpreterCommand.typeconstr;

import is.interpreterCommand.AbstractCommand;
import is.interpreterCommand.type.TypeCommand;
import is.visitor.Visitor;

public abstract class TypeconstrCommand extends AbstractCommand {

    TypeCommand shape;

    public TypeCommand getShape() {
        return shape;
    }
}
