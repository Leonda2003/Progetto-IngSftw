package is.system.prompt.visitor;

import is.system.prompt.parser.analyzer.Token;
import is.system.cmd.Cmd;
import is.system.cmd.CmdHandler;
import is.system.exception.SyntaxException;
import is.system.prompt.grammarCommand.*;
import is.system.prompt.grammarCommand.area.AreaAllGrammarCommand;
import is.system.prompt.grammarCommand.area.AreaIDGrammarCommand;
import is.system.prompt.grammarCommand.area.AreaTypeGrammarCommand;
import is.system.prompt.grammarCommand.list.*;
import is.system.prompt.grammarCommand.move.MoveGrammarCommand;
import is.system.prompt.grammarCommand.move.MoveOffGrammarCommand;
import is.system.prompt.grammarCommand.perimeter.PerimeterAllGrammarCommand;
import is.system.prompt.grammarCommand.perimeter.PerimeterIDGrammarCommand;
import is.system.prompt.grammarCommand.perimeter.PerimeterTypeGrammarCommand;
import is.system.prompt.grammarCommand.terminal.*;
import is.system.prompt.grammarCommand.type.TypeGrammarCommand;
import is.system.prompt.grammarCommand.typeconstr.CircleGrammarCommand;
import is.system.prompt.grammarCommand.typeconstr.ImageGrammarCommand;
import is.system.prompt.grammarCommand.typeconstr.RectangleGrammarCommand;
import is.system.prompt.grammarCommand.typeconstr.TypeconstrGrammarCommand;
import is.system.shapes.model.*;
import is.system.shapes.specificCmd.*;
import is.system.shapes.view.GraphicObjectPanel;

import javax.swing.*;
import java.awt.geom.Point2D;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.HashMap;


public class CommandVisitor implements Visitor{

    private final Context context = Context.CONTEXT;
    private final CmdHandler cmdHandler;

    public CommandVisitor(CmdHandler cmdHandler){
        this.cmdHandler = cmdHandler;
    }




    @Override
    public void interpret(CreateGrammarCommand c) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        float[] pos = interpret(c.getPos());
        Point2D position = new Point2D.Float(pos[0],pos[1]);
        GraphicObject go;
        WrapTypeConstr<GraphicObject> wrapTypeConstr = interpret(c.getTypeconstr());
        Constructor<NewObjectCmd> command = (Constructor<NewObjectCmd>) interpret(c.getNEW());
        switch (wrapTypeConstr.type()){
            case "Circle":

                double r = Double.parseDouble(wrapTypeConstr.param());
                Constructor<CircleObject> constructorC = (Constructor<CircleObject>) wrapTypeConstr.constructor();
                go = constructorC.newInstance(position,r);
                break;

            case "Rectangle":

                String[] param = wrapTypeConstr.param().split("-");
                double w = Double.parseDouble(param[0]);
                double h = Double.parseDouble(param[1]);
                Constructor<RectangleObject> constructorR = (Constructor<RectangleObject>) wrapTypeConstr.constructor();
                go = constructorR.newInstance(position,w,h);
                break;

            case "Image":

                Constructor<ImageObject> constructorI = (Constructor<ImageObject>) wrapTypeConstr.constructor();

                try{
                    if(Files.exists(Paths.get(wrapTypeConstr.param()))){
                        ImageIcon imageIcon = new ImageIcon(wrapTypeConstr.param());
                        go = constructorI.newInstance(imageIcon,position);
                        break;
                    }else throw new SyntaxException("Insert a valid Path");
                }catch (InvalidPathException e){
                    throw new SyntaxException("Insert a valid Path");
                }



            default:
                throw new IllegalStateException("Unexpected value: " + wrapTypeConstr.type());
        }
        cmdHandler.handle(command.newInstance(context.getGraphicObjectPanel(),go));
    }


    @Override
    public void interpret(RemoveGrammarCommand c) throws InvocationTargetException, InstantiationException, IllegalAccessException {
        String id = interpret(c.getObjID());
        context.NotContainShape(id);
        Constructor<RemoveObjectCmd> command = (Constructor<RemoveObjectCmd>) interpret(c.getDel());
        cmdHandler.handle(command.newInstance(context.getGraphicObjectPanel(),id));
    }

    @Override
    public void interpret(MoveGrammarCommand c) throws InvocationTargetException, InstantiationException, IllegalAccessException {

        Constructor<MoveCmd> command = (Constructor<MoveCmd>) interpret(c.getMv());
        String id = interpret(c.getObjID());
        float[] pos = interpret(c.getPos());
        GraphicObject go = context.getGraphicObject(id);
        cmdHandler.handle(command.newInstance(go,new Point2D.Float(pos[0],pos[1])));
    }

    @Override
    public void interpret(MoveOffGrammarCommand c) throws InvocationTargetException, InstantiationException, IllegalAccessException {

        Constructor<MoveOffCmd> command = (Constructor<MoveOffCmd>) interpret(c.getMvoff());
        String id = interpret(c.getObjID());
        GraphicObject go = context.getGraphicObject(id);
        float[] pos = interpret(c.getPos());

        cmdHandler.handle(command.newInstance(go,new Point2D.Float(pos[0],pos[1])));
    }

    @Override
    public void interpret(ScaleGrammarCommand c) throws InvocationTargetException, InstantiationException, IllegalAccessException {

        Constructor<ZoomCmd> command = (Constructor<ZoomCmd>) interpret(c.getScale());
        String id = interpret(c.getObjID());
        double factor = interpret(c.getPosfloat());
        GraphicObject go = context.getGraphicObject(id);
        cmdHandler.handle(command.newInstance(go,factor));
    }

    @Override
    public void interpret(ListObjIDGrammarCommand c) throws InvocationTargetException, InstantiationException, IllegalAccessException {

        Constructor<ListCmd> command = (Constructor<ListCmd>) interpret(c.getLs());
        String id = interpret(c.getObjID());
        cmdHandler.handle(command.newInstance(id,c.getObjID().getToken()));
    }

    @Override
    public void interpret(ListTypeGrammarCommand c) throws InvocationTargetException, InstantiationException, IllegalAccessException {

        Constructor<ListCmd> command = (Constructor<ListCmd>) interpret(c.getLs());
        cmdHandler.handle(command.newInstance("",c.getTypeCommand().getToken()));
    }

    @Override
    public void interpret(ListAllGrammarCommand c) throws InvocationTargetException, InstantiationException, IllegalAccessException {

        Constructor<ListCmd> command = (Constructor<ListCmd>) interpret(c.getLs());
        cmdHandler.handle(command.newInstance("",interpret(c.getAll())));
    }

    @Override
    public void interpret(ListGroupsGrammarCommand c) throws InvocationTargetException, InstantiationException, IllegalAccessException {

        Constructor<ListCmd> command = (Constructor<ListCmd>) interpret(c.getLs());
        cmdHandler.handle(command.newInstance("",interpret(c.getGroups())));
    }

    @Override
    public void interpret(GroupGrammarCommand c) throws InvocationTargetException, InstantiationException, IllegalAccessException {
        HashMap<String,GraphicObject> listg= interpret(c.getListIDCommand());
        GroupObject grp = new GroupObject(listg);
        Constructor<GroupCmd> command = (Constructor<GroupCmd>) interpret(c.getGrp());
        cmdHandler.handle(command.newInstance(context.getGraphicObjectPanel(),grp));
    }

    @Override
    public void interpret(UngroupGrammarCommand c) throws InvocationTargetException, InstantiationException, IllegalAccessException {
        String id = interpret(c.getObjID());
        GroupObject group = context.getGroupObject(id);
        Constructor<UngroupCmd> command = (Constructor<UngroupCmd>) interpret(c.getUngrp());
        cmdHandler.handle(command.newInstance(group,id,context.getGraphicObjectPanel()));
    }

    @Override
    public void interpret(AreaIDGrammarCommand c) throws InvocationTargetException, InstantiationException, IllegalAccessException {

        String id = interpret(c.getObjID());
        Constructor<AreaCmd> command = (Constructor<AreaCmd>) interpret(c.getArea());
        cmdHandler.handle(command.newInstance(id,c.getObjID().getToken()));
    }

    @Override
    public void interpret(AreaTypeGrammarCommand c) throws InvocationTargetException, InstantiationException, IllegalAccessException {

        Constructor<AreaCmd> command = (Constructor<AreaCmd>) interpret(c.getArea());
        cmdHandler.handle(command.newInstance("",c.getType().getToken()));
    }

    @Override
    public void interpret(AreaAllGrammarCommand c) throws InvocationTargetException, InstantiationException, IllegalAccessException {

        Constructor<AreaCmd> command = (Constructor<AreaCmd>) interpret(c.getArea());
        cmdHandler.handle(command.newInstance("",c.getAll().getToken()));
    }

    @Override
    public void interpret(PerimeterIDGrammarCommand c) throws InvocationTargetException, InstantiationException, IllegalAccessException {

        String id = interpret(c.getObjID());
        Constructor<PerimeterCmd> command = (Constructor<PerimeterCmd>) interpret(c.getPerimeter());
        cmdHandler.handle(command.newInstance(id,c.getObjID().getToken()));
    }


    @Override
    public void interpret(PerimeterTypeGrammarCommand c) throws InvocationTargetException, InstantiationException, IllegalAccessException {

        Constructor<PerimeterCmd> command = (Constructor<PerimeterCmd>) interpret(c.getPerimeter());
        cmdHandler.handle(command.newInstance("",c.getType().getToken()));
    }

    @Override
    public void interpret(PerimeterAllGrammarCommand c) throws InvocationTargetException, InstantiationException, IllegalAccessException {

        Constructor<PerimeterCmd> command = (Constructor<PerimeterCmd>) interpret(c.getPerimeter());
        cmdHandler.handle(command.newInstance("",c.getAll().getToken()));
    }

    @Override
    public WrapTypeConstr<GraphicObject> interpret(TypeconstrGrammarCommand typeconstrCommand) throws NoSuchMethodException {
        String param;
        WrapTypeConstr<GraphicObject> wrapTypeConstr;
        String type = interpret(typeconstrCommand.getShape());
        switch (type){
            case "Circle":
                CircleGrammarCommand circleCommand= (CircleGrammarCommand) typeconstrCommand;
                param = String.valueOf(interpret(circleCommand.getPosfloat()));
                wrapTypeConstr = new WrapTypeConstr<>(CircleObject.class.getConstructor(Point2D.class, double.class), param, type);
                break;
            case "Rectangle":
                RectangleGrammarCommand rectangleCommand= (RectangleGrammarCommand) typeconstrCommand;
                float[] pos  = interpret(rectangleCommand.getPos());
                param = ""+pos[0]+"-"+pos[1];
                wrapTypeConstr = new WrapTypeConstr<>(RectangleObject.class.getConstructor(Point2D.class, double.class, double.class), param, type);
                break;
            case "Image":
                ImageGrammarCommand imageCommand = (ImageGrammarCommand) typeconstrCommand;
                param =  interpret(imageCommand.getPath());
                wrapTypeConstr = new WrapTypeConstr<>(ImageObject.class.getConstructor(ImageIcon.class, Point2D.class), param, type);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }
        return wrapTypeConstr;
    }



    @Override
    public HashMap<String, GraphicObject> interpret(ListIDGrammarCommand c) {
        HashMap<String,GraphicObject> group = new HashMap<>();
        for(ObjID objid : c.getListObjID()){
            String id = interpret(objid);
            group.put(id,context.getGraphicObject(id));
        }
        return group;
    }

    @Override
    public float[] interpret(PosGrammarCommand c) {return new float[]{interpret(c.getPosfloat1()),interpret(c.getPosfloat2())};}


    @Override
    public String interpret(TypeGrammarCommand c) {
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
    public Constructor<? extends Cmd> interpret(TerminalGrammarCommand c) {
        try{
            switch (c.getToken()){
                case NEW:
                    return NewObjectCmd.class.getConstructor(GraphicObjectPanel.class,GraphicObject.class);
                case DEL:
                    return RemoveObjectCmd.class.getConstructor(GraphicObjectPanel.class,String.class);
                case MV:
                    return MoveCmd.class.getConstructor(GraphicObject.class,Point2D.class);
                case MVOFF:
                    return MoveOffCmd.class.getConstructor(GraphicObject.class,Point2D.class);
                case SCALE:
                    return ZoomCmd.class.getConstructor(GraphicObject.class, double.class);
                case LS:
                    return ListCmd.class.getConstructor(String.class, Token.class);
                case GRP:
                    return GroupCmd.class.getConstructor(GraphicObjectPanel.class,GroupObject.class);
                case UNGRP:
                    return UngroupCmd.class.getConstructor(GroupObject.class,String.class,GraphicObjectPanel.class);
                case AREA:
                    return AreaCmd.class.getConstructor(String.class, Token.class);
                case PERIMETER:
                    return PerimeterCmd.class.getConstructor(String.class, Token.class);
                default: throw new SyntaxException("ERROR");
            }
        }catch (NoSuchMethodException | SyntaxException e){e.printStackTrace();}
        return null;
    }

}
