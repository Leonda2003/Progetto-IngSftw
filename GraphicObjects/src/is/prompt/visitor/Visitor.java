package is.prompt.visitor;

import is.prompt.parser.analyzer.Token;
import is.cmd.Cmd;
import is.prompt.interpreterCommand.*;
import is.prompt.interpreterCommand.area.AreaAllCommand;
import is.prompt.interpreterCommand.area.AreaIDCommand;
import is.prompt.interpreterCommand.area.AreaTypeCommand;
import is.prompt.interpreterCommand.list.*;
import is.prompt.interpreterCommand.move.MoveCommand;
import is.prompt.interpreterCommand.move.MoveOffCommand;
import is.prompt.interpreterCommand.perimeter.PerimeterAllCommand;
import is.prompt.interpreterCommand.perimeter.PerimeterIDCommand;
import is.prompt.interpreterCommand.perimeter.PerimeterTypeCommand;
import is.prompt.interpreterCommand.terminal.*;
import is.prompt.interpreterCommand.type.TypeCommand;
import is.prompt.interpreterCommand.typeconstr.TypeconstrCommand;

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
