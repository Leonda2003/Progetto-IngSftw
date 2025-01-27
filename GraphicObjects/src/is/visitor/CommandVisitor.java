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
import is.shapes.specificcommand.*;

import java.lang.reflect.Constructor;

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
    public Constructor<? extends Cmd> interpret(TerminalCommand c) {
        try{
            switch (c.getToken()){
                case NEW:
                    return NewObjectCmd.class.getConstructor();
                case DEL:
                    return RemoveObjectCmd.class.getConstructor();
                case MV:
                    return MoveCmd.class.getConstructor();
                case MVOFF:
                    return MoveCmd.class.getConstructor();
                case SCALE:
                    return ZoomCmd.class.getConstructor();
                case LS:
                    return ListCmd.class.getConstructor();
                case ALL:
                    break;
                case GROUPS:
                    break;
                case GRP:
                    return GroupCmd.class.getConstructor();
                case UNGRP:
                    return UngroupCmd.class.getConstructor();
                case AREA:
                    return NewObjectCmd.class.getConstructor();
                case PERIMETER:
                    return NewObjectCmd.class.getConstructor();
                case OBJ_ID:
                    break;
                case POSFLOAT:
                    break;
                case PATH:
                    break;
            }

        }catch (NoSuchMethodException e){e.printStackTrace();}
        return null;
    }

}
