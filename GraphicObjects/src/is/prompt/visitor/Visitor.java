package is.prompt.visitor;

import is.prompt.parser.analyzer.Token;
import is.cmd.Cmd;
import is.prompt.grammarCommand.*;
import is.prompt.grammarCommand.area.AreaAllCommand;
import is.prompt.grammarCommand.area.AreaIDCommand;
import is.prompt.grammarCommand.area.AreaTypeCommand;
import is.prompt.grammarCommand.list.*;
import is.prompt.grammarCommand.move.MoveCommand;
import is.prompt.grammarCommand.move.MoveOffCommand;
import is.prompt.grammarCommand.perimeter.PerimeterAllCommand;
import is.prompt.grammarCommand.perimeter.PerimeterIDCommand;
import is.prompt.grammarCommand.perimeter.PerimeterTypeCommand;
import is.prompt.grammarCommand.terminal.*;
import is.prompt.grammarCommand.type.TypeCommand;
import is.prompt.grammarCommand.typeconstr.TypeconstrCommand;
import is.shapes.model.GraphicObject;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

public interface Visitor {

    void interpret(CreateCommand c) throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException;
    void interpret(RemoveCommand c) throws InvocationTargetException, InstantiationException, IllegalAccessException;
    void interpret(MoveCommand c) throws InvocationTargetException, InstantiationException, IllegalAccessException;
    void interpret(MoveOffCommand c) throws InvocationTargetException, InstantiationException, IllegalAccessException;
    void interpret(ScaleCommand c) throws InvocationTargetException, InstantiationException, IllegalAccessException;
    void interpret(ListObjIDCommand c) throws InvocationTargetException, InstantiationException, IllegalAccessException;
    void interpret(ListTypeCommand c) throws InvocationTargetException, InstantiationException, IllegalAccessException;
    void interpret(ListAllCommand c) throws InvocationTargetException, InstantiationException, IllegalAccessException;
    void interpret(ListGroupsCommand c) throws InvocationTargetException, InstantiationException, IllegalAccessException;
    void interpret(GroupCommand c) throws InvocationTargetException, InstantiationException, IllegalAccessException;
    void interpret(UngroupCommand c) throws InvocationTargetException, InstantiationException, IllegalAccessException;
    void interpret(AreaIDCommand c) throws InvocationTargetException, InstantiationException, IllegalAccessException;
    void interpret(AreaTypeCommand c) throws InvocationTargetException, InstantiationException, IllegalAccessException;
    void interpret(AreaAllCommand c) throws InvocationTargetException, InstantiationException, IllegalAccessException;
    void interpret(PerimeterIDCommand c) throws InvocationTargetException, InstantiationException, IllegalAccessException;
    void interpret(PerimeterTypeCommand c) throws InvocationTargetException, InstantiationException, IllegalAccessException;
    void interpret(PerimeterAllCommand c) throws InvocationTargetException, InstantiationException, IllegalAccessException;
    float[] interpret(PosCommand c);
    CommandVisitor.WrapTypeConstr interpret(TypeconstrCommand c) throws NoSuchMethodException;
    String interpret(TypeCommand c);
    float interpret(Posfloat c);
    String interpret(ObjID c);
    String interpret(Path c);
    HashMap<String, GraphicObject> interpret(ListIDCommand c);
    Token interpret(All_Groups c);
    Constructor<? extends Cmd> interpret(TerminalCommand c);
}
