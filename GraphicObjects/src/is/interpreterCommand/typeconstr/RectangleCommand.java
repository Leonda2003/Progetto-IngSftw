package is.interpreterCommand.typeconstr;

import is.interpreterCommand.PosCommand;
import is.interpreterCommand.type.TypeCommand;
import is.visitor.Visitor;

public class RectangleCommand extends TypeconstrCommand{

    PosCommand pos;

    public RectangleCommand(TypeCommand rectangle, PosCommand pos) {
        this.shape = rectangle;
        this.pos = pos;
    }

    @Override
    public String toString() {
        return ""+ shape + pos+" ";
    }




    public PosCommand getPos() {
        return pos;
    }
}
