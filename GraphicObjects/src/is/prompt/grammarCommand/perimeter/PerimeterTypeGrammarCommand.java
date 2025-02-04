package is.prompt.grammarCommand.perimeter;

import is.prompt.grammarCommand.terminal.TerminalGrammarCommand;
import is.prompt.grammarCommand.type.TypeGrammarCommand;
import is.prompt.visitor.Visitor;

import java.lang.reflect.InvocationTargetException;

public class PerimeterTypeGrammarCommand extends PerimeterGrammarCommand {

    TypeGrammarCommand type;

    public PerimeterTypeGrammarCommand(TerminalGrammarCommand perimeter, TypeGrammarCommand type) {
        this.perimeter = perimeter;
        this.type = type;
    }

    @Override
    public String toString() {
        return "PerimeterCommand: "+perimeter + type;
    }

    @Override
    public void accept(Visitor v) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        v.interpret(this);
    }

    public TypeGrammarCommand getType() {
        return type;
    }
}
