package is.visitor;

import is.analyzer.Token;
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
import is.interpreterCommand.terminal.*;
import is.interpreterCommand.type.TypeCommand;
import is.interpreterCommand.typeconstr.TypeconstrCommand;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

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
    void interpret(GroupCommand c);
    void interpret(UngroupCommand c);
    void interpret(AreaIDCommand c);
    void interpret(AreaTypeCommand c);
    void interpret(AreaAllCommand c);
    void interpret(PerimeterIDCommand c);
    void interpret(PerimeterTypeCommand c);
    void interpret(PerimeterAllCommand c);
    float[] interpret(PosCommand c);
    CommandVisitor.WrapTypeConstr interpret(TypeconstrCommand c) throws NoSuchMethodException;
    String interpret(TypeCommand c);
    float interpret(Posfloat c);
    String interpret(ObjID c);
    String interpret(Path c);
    void interpret(ListIDCommand c);
    Token interpret(All_Groups c);
    Constructor<? extends Cmd> interpret(TerminalCommand c);
}
