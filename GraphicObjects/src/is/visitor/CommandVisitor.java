package is.visitor;

import is.TestGraphics2;
import is.analyzer.Token;
import is.cmd.Cmd;
import is.cmd.CmdHandler;
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
import is.interpreterCommand.terminal.*;
import is.interpreterCommand.type.TypeCommand;
import is.interpreterCommand.typeconstr.CircleCommand;
import is.interpreterCommand.typeconstr.ImageCommand;
import is.interpreterCommand.typeconstr.RectangleCommand;
import is.interpreterCommand.typeconstr.TypeconstrCommand;
import is.shapes.model.CircleObject;
import is.shapes.model.GraphicObject;
import is.shapes.model.ImageObject;
import is.shapes.model.RectangleObject;
import is.shapes.prompt.GraphicObjectCommandPrompt;
import is.shapes.specificCmd.*;
import is.shapes.view.GraphicObjectPanel;

import javax.swing.*;
import java.awt.geom.Point2D;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class CommandVisitor implements Visitor{

    private final GraphicObjectPanel panel ;

    private final GraphicObjectCommandPrompt prompt;

    private final CmdHandler cmdHandler;

    public CommandVisitor(GraphicObjectPanel panel,CmdHandler cmdHandler,GraphicObjectCommandPrompt prompt){
        this.cmdHandler = cmdHandler;
        this.panel = panel;
        this.prompt = prompt;
    }


    @Override
    public void interpret(CreateCommand c) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        float[] pos = interpret(c.getPos());
        Point2D position = new Point2D.Float(pos[0],pos[1]);
        GraphicObject go=null;
        WrapTypeConstr wrapTypeConstr = interpret(c.getTypeconstr());
        Constructor<NewObjectCmd> command = (Constructor<NewObjectCmd>) interpret(c.getNEW());
        switch (wrapTypeConstr.getType()){
            case "Circle":

                double r = Double.parseDouble(wrapTypeConstr.getParam());
                Constructor<CircleObject> constructorC = (Constructor<CircleObject>) wrapTypeConstr.getConstructor();
                go = constructorC.newInstance(position,r);
                break;

            case "Rectangle":

                String[] param = wrapTypeConstr.getParam().split("-");
                double w = Double.parseDouble(param[0]);
                double h = Double.parseDouble(param[1]);
                Constructor<RectangleObject> constructorR = (Constructor<RectangleObject>) wrapTypeConstr.getConstructor();
                go = constructorR.newInstance(position,w,h);
                break;

            case "Image":

                Constructor<ImageObject> constructorI = (Constructor<ImageObject>) wrapTypeConstr.getConstructor();
                ImageIcon imageIcon = new ImageIcon(wrapTypeConstr.getParam());
                go = constructorI.newInstance(imageIcon,position);
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + wrapTypeConstr.getType());
        }
        cmdHandler.handle(command.newInstance(panel,go));
    }


    @Override
    public void interpret(RemoveCommand c) throws InvocationTargetException, InstantiationException, IllegalAccessException {
        String id = interpret(c.getObjID());
        Context.CONTEXT.NotContainShape(id);
        Constructor<RemoveObjectCmd> command = (Constructor<RemoveObjectCmd>) interpret(c.getDel());
        cmdHandler.handle(command.newInstance(panel,id));
    }

    @Override
    public void interpret(MoveCommand c) throws InvocationTargetException, InstantiationException, IllegalAccessException {

        Constructor<MoveCmd> command = (Constructor<MoveCmd>) interpret(c.getMv());
        String id = interpret(c.getObjID());
        float[] pos = interpret(c.getPos());
        GraphicObject go = Context.CONTEXT.getGraphicObject(id);
        cmdHandler.handle(command.newInstance(go,new Point2D.Float(pos[0],pos[1])));
    }

    @Override
    public void interpret(MoveOffCommand c) throws InvocationTargetException, InstantiationException, IllegalAccessException {

        Constructor<MoveCmd> command = (Constructor<MoveCmd>) interpret(c.getMvoff());
        String id = interpret(c.getObjID());
        float[] pos = interpret(c.getPos());
        GraphicObject go = Context.CONTEXT.getGraphicObject(id);
        cmdHandler.handle(command.newInstance(go,new Point2D.Double(go.getPosition().getX()+pos[0],go.getPosition().getX()+pos[1])));
    }

    @Override
    public void interpret(ScaleCommand c) throws InvocationTargetException, InstantiationException, IllegalAccessException {

        Constructor<ZoomCmd> command = (Constructor<ZoomCmd>) interpret(c.getScale());
        String id = interpret(c.getObjID());
        double factor = interpret(c.getPosfloat());
        GraphicObject go = Context.CONTEXT.getGraphicObject(id);
        cmdHandler.handle(command.newInstance(go,factor));
    }

    @Override
    public void interpret(ListObjIDCommand c) throws InvocationTargetException, InstantiationException, IllegalAccessException {

        Constructor<ListCmd> command = (Constructor<ListCmd>) interpret(c.getLs());
        String id = interpret(c.getObjID());
        cmdHandler.handle(command.newInstance(id,c.getObjID().getToken(),prompt));

    }

    @Override
    public void interpret(ListTypeCommand c) throws InvocationTargetException, InstantiationException, IllegalAccessException {

        Constructor<ListCmd> command = (Constructor<ListCmd>) interpret(c.getLs());
        cmdHandler.handle(command.newInstance("",c.getTypeCommand().getToken(),prompt));
    }

    @Override
    public void interpret(ListAllCommand c) throws InvocationTargetException, InstantiationException, IllegalAccessException {

        Constructor<ListCmd> command = (Constructor<ListCmd>) interpret(c.getLs());
        cmdHandler.handle(command.newInstance("",interpret(c.getAll()),prompt));
    }

    @Override
    public void interpret(ListGroupsCommand c) throws InvocationTargetException, InstantiationException, IllegalAccessException {

        Constructor<ListCmd> command = (Constructor<ListCmd>) interpret(c.getLs());
        cmdHandler.handle(command.newInstance("",interpret(c.getGroups()),prompt));
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
    public WrapTypeConstr interpret(TypeconstrCommand typeconstrCommand) throws NoSuchMethodException {
        String param;
        WrapTypeConstr wrapTypeConstr;
        String type = interpret(typeconstrCommand.getShape());
        switch (type){
            case "Circle":
                CircleCommand circleCommand= (CircleCommand) typeconstrCommand;
                param = String.valueOf(interpret(circleCommand.getPosfloat()));
                wrapTypeConstr = new WrapTypeConstr(CircleObject.class.getConstructor(Point2D.class,double.class),param,type);
                break;
            case "Rectangle":
                RectangleCommand rectangleCommand= (RectangleCommand) typeconstrCommand;
                float[] pos  = interpret(rectangleCommand.getPos());
                param = STR."\{pos[0]}-\{pos[1]}";
                wrapTypeConstr = new WrapTypeConstr(RectangleObject.class.getConstructor(Point2D.class,double.class,double.class),param,type);
                break;
            case "Image":
                ImageCommand imageCommand = (ImageCommand) typeconstrCommand;
                param =  interpret(imageCommand.getPath());
                wrapTypeConstr = new WrapTypeConstr(ImageObject.class.getConstructor(ImageIcon.class, Point2D.class),param,type);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }
        return wrapTypeConstr;
    }

    @Override
    public void interpret(PerimeterTypeCommand c) {

    }

    @Override
    public void interpret(PerimeterAllCommand c) {

    }

    @Override
    public void interpret(ListIDCommand c) {

    }

    @Override
    public float[] interpret(PosCommand c) {return new float[]{interpret(c.getPosfloat1()),interpret(c.getPosfloat2())};}


    @Override
    public String interpret(TypeCommand c) {
            switch (c.getToken()){
                case CIRCLE:
                    return "Circle";
                case RECTANGLE:
                    return "Rectangle";
                case IMG:
                    return "Image";
                default: throw new SyntaxException("ERROR");
            }
    }

    @Override
    public float interpret(Posfloat c) {return c.getPosfloat();}

    @Override
    public String interpret(ObjID c) { return c.getID();}

    @Override
    public String interpret(Path c) {return c.getPath();}


    @Override
    public Token interpret(All_Groups c) {
        return c.getToken();
    }


    @Override
    public Constructor<? extends Cmd> interpret(TerminalCommand c) {
        try{
            switch (c.getToken()){
                case NEW:
                    return NewObjectCmd.class.getConstructor(GraphicObjectPanel.class,GraphicObject.class);
                case DEL:
                    return RemoveObjectCmd.class.getConstructor(GraphicObjectPanel.class,String.class);
                case MV:
                case MVOFF:
                    return MoveCmd.class.getConstructor(GraphicObject.class,Point2D.class);
                case SCALE:
                    return ZoomCmd.class.getConstructor(GraphicObject.class, double.class);
                case LS:
                    return ListCmd.class.getConstructor(String.class, Token.class,GraphicObjectCommandPrompt.class);
                case GRP:
                    return GroupCmd.class.getConstructor();
                case UNGRP:
                    return UngroupCmd.class.getConstructor();
                case AREA:
                    return NewObjectCmd.class.getConstructor();
                case PERIMETER:
                    return NewObjectCmd.class.getConstructor();
                default: throw new SyntaxException("ERROR");
            }
        }catch (NoSuchMethodException | SyntaxException e){e.printStackTrace();}
        return null;
    }



    class WrapTypeConstr{

        Constructor<? extends GraphicObject> constructor;
        String param;
        String type;

        WrapTypeConstr(Constructor<? extends GraphicObject> constructor,String param,String type){
            this.constructor = constructor;
            this.param= param;
            this.type = type;
        }

        public String getParam() {
            return param;
        }

        public Constructor<? extends GraphicObject> getConstructor() {
            return constructor;
        }

        public String getType() {
            return type;
        }
    }




}
