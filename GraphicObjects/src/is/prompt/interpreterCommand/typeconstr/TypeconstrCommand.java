package is.prompt.interpreterCommand.typeconstr;

import is.prompt.interpreterCommand.AbstractCommand;
import is.prompt.interpreterCommand.type.TypeCommand;

public abstract class TypeconstrCommand extends AbstractCommand {

    TypeCommand shape;

    public TypeCommand getShape() {
        return shape;
    }
}
