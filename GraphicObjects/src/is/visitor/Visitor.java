package is.visitor;

import is.cmd.Cmd;
import is.interpreterCommand.*;
import is.interpreterCommand.area.AreaCommand;
import is.interpreterCommand.list.ListCommand;
import is.interpreterCommand.move.MovementCommand;
import is.interpreterCommand.perimeter.PerimeterCommand;
import is.interpreterCommand.terminal.TerminalCommand;
import is.interpreterCommand.type.TypeCommand;
import is.interpreterCommand.typeconstr.TypeconstrCommand;

import java.lang.reflect.Constructor;

public interface Visitor {

    void interpret(CreateCommand c);
    void interpret(RemoveCommand c);
    void interpret(MovementCommand c);
    void interpret(ScaleCommand c);
    void interpret(ListCommand c);
    void interpret(GroupCommand c);
    void interpret(UngroupCommand c);
    void interpret(AreaCommand c);
    void interpret(PerimeterCommand c);
    void interpret(PosCommand c);
    void interpret(TypeconstrCommand c);
    void interpret(TypeCommand c);
    void interpret(ListIDCommand c);
    Constructor<? extends Cmd> interpret(TerminalCommand c);
}
