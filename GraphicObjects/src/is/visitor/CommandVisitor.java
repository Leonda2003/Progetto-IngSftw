package is.visitor;

import is.cmd.Cmd;
import is.exception.SyntaxException;
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
import is.shapes.model.CircleObject;
import is.shapes.model.GraphicObject;
import is.shapes.model.ImageObject;
import is.shapes.model.RectangleObject;
import is.shapes.specificCmd.*;

import javax.swing.*;
import java.awt.geom.Point2D;
import java.lang.reflect.Constructor;

public class CommandVisitor implements Visitor{


    @Override
    public void interpret(CreateCommand c) {

    }

    @Override
    public void interpret(RemoveCommand c) {

    }

    @Override
    public void interpret(MoveCommand c) {

    }

    @Override
    public void interpret(MoveOffCommand c) {

    }

    @Override
    public void interpret(ScaleCommand c) {

    }

    @Override
    public void interpret(ListObjIDCommand c) {

    }

    @Override
    public void interpret(ListTypeCommand c) {

    }

    @Override
    public void interpret(ListAllCommand c) {

    }

    @Override
    public void interpret(ListGroupsCommand c) {

    }

    @Override
    public void interpret(GroupCommand c) {

    }

    @Override
    public void interpret(UngroupCommand c) {

    }

    @Override
    public void interpret(AreaIDCommand c) {

    }

    @Override
    public void interpret(AreaTypeCommand c) {

    }

    @Override
    public void interpret(AreaAllCommand c) {

    }

    @Override
    public void interpret(PerimeterIDCommand c) {

    }

    @Override
    public void interpret(PerimeterTypeCommand c) {

    }

    @Override
    public void interpret(PerimeterAllCommand c) {

    }

    @Override
    public float[] interpret(PosCommand c) {


        return new float[interpret(c.getPosfloat1()),interpret(c.getPosfloat2())];
    }

    @Override
    public void interpret(RectangleCommand c) {
        return this.interpret(c.getRectangle()).newInstance(this.interpret(c.getPos())[0],0,0);
    }

    @Override
    public void interpret(CircleCommand c) {

    }

    @Override
    public void interpret(ImageCommand c) {

    }

    @Override
    public Constructor<? extends GraphicObject> interpret(TypeCommand c) {
        try{
            switch (c.getToken()){
                case CIRCLE:
                    return CircleObject.class.getConstructor(Point2D.class, double.class);
                case RECTANGLE:
                    return RectangleObject.class.getConstructor(Point2D.class, double.class, double.class);
                case IMG:
                    return ImageObject.class.getConstructor(ImageIcon.class, Point2D.class);
                default: throw new SyntaxException("ERROR");
            }
        }catch (NoSuchMethodException | SyntaxException e){e.printStackTrace();}
        return null;
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
                default: throw new SyntaxException("ERROR");
            }
        }catch (NoSuchMethodException | SyntaxException e){e.printStackTrace();}
        return null;
    }



}
