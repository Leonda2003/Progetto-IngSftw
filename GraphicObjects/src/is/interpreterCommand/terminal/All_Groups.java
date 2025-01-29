package is.interpreterCommand.terminal;

import is.analyzer.Token;
import is.visitor.Visitor;

import java.lang.reflect.InvocationTargetException;

public class All_Groups extends TerminalCommand{
    public All_Groups(Token token) {
        super(token);
    }

    @Override
    public void accept(Visitor v) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        v.interpret(this);
    }

}
