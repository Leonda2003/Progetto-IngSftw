package is.system.prompt.grammarCommand.typeconstr;


import is.system.prompt.grammarCommand.GrammarCommand;
import is.system.prompt.grammarCommand.type.TypeGrammarCommand;
import is.system.prompt.visitor.Visitor;

import java.lang.reflect.InvocationTargetException;

public abstract class TypeconstrGrammarCommand implements GrammarCommand {

    TypeGrammarCommand shape;

    public TypeGrammarCommand getShape() {
        return shape;
    }

    public void accept(Visitor v) throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        v.interpret(this);
    }
}
