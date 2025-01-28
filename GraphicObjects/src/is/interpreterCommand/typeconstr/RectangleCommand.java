package is.interpreterCommand.typeconstr;

import is.interpreterCommand.PosCommand;
import is.interpreterCommand.type.TypeCommand;
import is.visitor.Visitor;

public class RectangleCommand extends TypeconstrCommand{

    TypeCommand rectangle;
    PosCommand pos;

    public RectangleCommand(TypeCommand rectangle, PosCommand pos) {
        this.rectangle = rectangle;
        this.pos = pos;
    }

    @Override
    public String toString() {
        return ""+ rectangle + pos+" ";
    }

    public void accept(Visitor v){
        v.interpret(this);
    }

    public TypeCommand getRectangle() {
        return rectangle;
    }

    public PosCommand getPos() {
        return pos;
    }
}
