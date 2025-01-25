package is.visitor;

import is.interpreterCommand.*;
import is.interpreterCommand.area.AreaCommand;
import is.interpreterCommand.list.ListCommand;
import is.interpreterCommand.move.MovementCommand;
import is.interpreterCommand.perimeter.PerimeterCommand;
import is.interpreterCommand.terminal.TerminalCommand;
import is.interpreterCommand.type.TypeCommand;
import is.interpreterCommand.typeconstr.TypeconstrCommand;

public class CommandVisitor implements Visitor{
    @Override
    public void interpret(CreateCommand c) {

    }

    @Override
    public void interpret(RemoveCommand c) {

    }

    @Override
    public void interpret(MovementCommand c) {

    }

    @Override
    public void interpret(ScaleCommand c) {

    }

    @Override
    public void interpret(ListCommand c) {

    }

    @Override
    public void interpret(GroupCommand c) {

    }

    @Override
    public void interpret(UngroupCommand c) {

    }

    @Override
    public void interpret(AreaCommand c) {

    }

    @Override
    public void interpret(PerimeterCommand c) {

    }

    @Override
    public void interpret(PosCommand c) {

    }

    @Override
    public void interpret(TypeconstrCommand c) {

    }

    @Override
    public void interpret(TypeCommand c) {

    }

    @Override
    public void interpret(ListIDCommand c) {

    }

    @Override
    public void interpret(TerminalCommand c) {

    }
}
