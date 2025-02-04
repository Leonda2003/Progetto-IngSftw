package is.prompt.visitor;

import is.prompt.parser.analyzer.Token;
import is.cmd.Cmd;
import is.prompt.grammarCommand.*;
import is.prompt.grammarCommand.area.AreaAllGrammarCommand;
import is.prompt.grammarCommand.area.AreaIDGrammarCommand;
import is.prompt.grammarCommand.area.AreaTypeGrammarCommand;
import is.prompt.grammarCommand.list.*;
import is.prompt.grammarCommand.move.MoveGrammarCommand;
import is.prompt.grammarCommand.move.MoveOffGrammarCommand;
import is.prompt.grammarCommand.perimeter.PerimeterAllGrammarCommand;
import is.prompt.grammarCommand.perimeter.PerimeterIDGrammarCommand;
import is.prompt.grammarCommand.perimeter.PerimeterTypeGrammarCommand;
import is.prompt.grammarCommand.terminal.*;
import is.prompt.grammarCommand.type.TypeGrammarCommand;
import is.prompt.grammarCommand.typeconstr.TypeconstrGrammarCommand;
import is.shapes.model.GraphicObject;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

public interface Visitor {

    void interpret(CreateGrammarCommand c) throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException;
    void interpret(RemoveGrammarCommand c) throws InvocationTargetException, InstantiationException, IllegalAccessException;
    void interpret(MoveGrammarCommand c) throws InvocationTargetException, InstantiationException, IllegalAccessException;
    void interpret(MoveOffGrammarCommand c) throws InvocationTargetException, InstantiationException, IllegalAccessException;
    void interpret(ScaleGrammarCommand c) throws InvocationTargetException, InstantiationException, IllegalAccessException;
    void interpret(ListObjIDGrammarCommand c) throws InvocationTargetException, InstantiationException, IllegalAccessException;
    void interpret(ListTypeGrammarCommand c) throws InvocationTargetException, InstantiationException, IllegalAccessException;
    void interpret(ListAllGrammarCommand c) throws InvocationTargetException, InstantiationException, IllegalAccessException;
    void interpret(ListGroupsGrammarCommand c) throws InvocationTargetException, InstantiationException, IllegalAccessException;
    void interpret(GroupGrammarCommand c) throws InvocationTargetException, InstantiationException, IllegalAccessException;
    void interpret(UngroupGrammarCommand c) throws InvocationTargetException, InstantiationException, IllegalAccessException;
    void interpret(AreaIDGrammarCommand c) throws InvocationTargetException, InstantiationException, IllegalAccessException;
    void interpret(AreaTypeGrammarCommand c) throws InvocationTargetException, InstantiationException, IllegalAccessException;
    void interpret(AreaAllGrammarCommand c) throws InvocationTargetException, InstantiationException, IllegalAccessException;
    void interpret(PerimeterIDGrammarCommand c) throws InvocationTargetException, InstantiationException, IllegalAccessException;
    void interpret(PerimeterTypeGrammarCommand c) throws InvocationTargetException, InstantiationException, IllegalAccessException;
    void interpret(PerimeterAllGrammarCommand c) throws InvocationTargetException, InstantiationException, IllegalAccessException;
    float[] interpret(PosGrammarCommand c);
    CommandVisitor.WrapTypeConstr interpret(TypeconstrGrammarCommand c) throws NoSuchMethodException;
    String interpret(TypeGrammarCommand c);
    float interpret(Posfloat c);
    String interpret(ObjID c);
    String interpret(Path c);
    HashMap<String, GraphicObject> interpret(ListIDGrammarCommand c);
    Token interpret(All_Groups c);
    Constructor<? extends Cmd> interpret(TerminalGrammarCommand c);
}
