package is.interpreterCommand;

import is.visitor.Visitor;

import java.lang.reflect.InvocationTargetException;

public abstract class AbstractCommand implements Command{

    @Override
    public void accept(Visitor v) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

    }
}
