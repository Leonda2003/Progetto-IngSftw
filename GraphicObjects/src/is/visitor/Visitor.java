package is.visitor;

import is.cmd.Cmd;
import is.interpreterCommand.*;
import is.interpreterCommand.area.AreaAllCommand;
import is.interpreterCommand.area.AreaIDCommand;
import is.interpreterCommand.area.AreaTypeCommand;
import is.interpreterCommand.list.*;
import is.interpreterCommand.move.MoveCommand;
import is.interpreterCommand.move.MoveOffCommand;
import is.interpreterCommand.perimeter.PerimeterAllCommand;
import is.interpreterCommand.perimeter.PerimeterIDCommand;
import is.interpreterCommand.perimeter.PerimeterTypeCommand;
import is.interpreterCommand.terminal.TerminalCommand;
import is.interpreterCommand.type.TypeCommand;
import is.interpreterCommand.typeconstr.CircleCommand;
import is.interpreterCommand.typeconstr.ImageCommand;
import is.interpreterCommand.typeconstr.RectangleCommand;
import is.shapes.model.GraphicObject;

import java.lang.reflect.Constructor;

public interface Visitor {

    void interpret(CreateCommand c);
    void interpret(RemoveCommand c);
    void interpret(MoveCommand c);
    void interpret(MoveOffCommand c);
    void interpret(ScaleCommand c);
    void interpret(ListObjIDCommand c);
    void interpret(ListTypeCommand c);
    void interpret(ListAllCommand c);
    void interpret(ListGroupsCommand c);
    void interpret(GroupCommand c);
    void interpret(UngroupCommand c);
    void interpret(AreaIDCommand c);
    void interpret(AreaTypeCommand c);
    void interpret(AreaAllCommand c);
    void interpret(PerimeterIDCommand c);
    void interpret(PerimeterTypeCommand c);
    void interpret(PerimeterAllCommand c);
    float[] interpret(PosCommand c);
    void interpret(RectangleCommand c);
    void interpret(CircleCommand c);
    void interpret(ImageCommand c);
    Constructor<? extends GraphicObject> interpret(TypeCommand c);
    void interpret(ListIDCommand c);
    Constructor<? extends Cmd> interpret(TerminalCommand c);
}
