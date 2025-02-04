package is.prompt.grammarCommand;

import is.prompt.visitor.Visitor;

import java.lang.reflect.InvocationTargetException;

public abstract class AbstractGrammarCommand implements GrammarCommand {
    @Override
    public void accept(Visitor v) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

    }
}
