package is.system.prompt.grammarCommand;

import is.system.prompt.visitor.Visitor;

import java.lang.reflect.InvocationTargetException;

public interface GrammarCommand {
    void accept(Visitor v) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException;
}
