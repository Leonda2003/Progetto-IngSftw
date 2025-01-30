package is.prompt.interpreterCommand.typeconstr;

import is.prompt.interpreterCommand.PosCommand;
import is.prompt.interpreterCommand.type.TypeCommand;

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
