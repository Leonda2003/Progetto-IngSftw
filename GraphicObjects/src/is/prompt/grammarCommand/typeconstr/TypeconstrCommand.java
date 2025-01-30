package is.prompt.grammarCommand.typeconstr;

import is.prompt.grammarCommand.AbstractCommand;
import is.prompt.grammarCommand.type.TypeCommand;

public abstract class TypeconstrCommand extends AbstractCommand {

    TypeCommand shape;

    public TypeCommand getShape() {
        return shape;
    }
}
