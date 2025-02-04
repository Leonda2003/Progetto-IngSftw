package is.prompt.grammarCommand;

import is.prompt.visitor.Visitor;

import java.lang.reflect.InvocationTargetException;

public interface GrammarCommand {
    void accept(Visitor v) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException;
}
