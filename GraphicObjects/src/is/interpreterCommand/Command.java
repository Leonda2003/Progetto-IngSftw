package is.interpreterCommand;

import is.visitor.Visitor;

import java.lang.reflect.InvocationTargetException;

public interface Command {
    void accept(Visitor v) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException;
}
