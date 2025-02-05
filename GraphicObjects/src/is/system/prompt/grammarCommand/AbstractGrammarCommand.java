package is.system.prompt.grammarCommand;

import is.system.prompt.visitor.Visitor;

import java.lang.reflect.InvocationTargetException;

public abstract class AbstractGrammarCommand implements GrammarCommand {
    @Override
    public void accept(Visitor v) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

    }
}
