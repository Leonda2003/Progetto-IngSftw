package is.interpreterCommand;

import is.visitor.Visitor;

import java.lang.reflect.InvocationTargetException;

public abstract class AbstractCommand implements Command{

    @Override
    public String accept(Visitor v) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        return null;
    }
}
