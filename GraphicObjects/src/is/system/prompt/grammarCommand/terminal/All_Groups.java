package is.system.prompt.grammarCommand.terminal;

import is.system.prompt.parser.analyzer.Token;
import is.system.prompt.visitor.Visitor;

import java.lang.reflect.InvocationTargetException;

public class All_Groups extends TerminalGrammarCommand {
    public All_Groups(Token token) {
        super(token);
    }

    @Override
    public void accept(Visitor v) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        v.interpret(this);
    }

}
