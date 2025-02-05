package is.system.prompt.grammarCommand.perimeter;

import is.system.prompt.grammarCommand.terminal.TerminalGrammarCommand;
import is.system.prompt.visitor.Visitor;

import java.lang.reflect.InvocationTargetException;

public class PerimeterAllGrammarCommand extends PerimeterGrammarCommand {

    TerminalGrammarCommand all;

    public PerimeterAllGrammarCommand(TerminalGrammarCommand perimeter, TerminalGrammarCommand all) {
        this.perimeter = perimeter;
        this.all = all;
    }

    public TerminalGrammarCommand getAll() {
        return all;
    }

    @Override
    public void accept(Visitor v) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        v.interpret(this);
    }

    @Override
    public String toString() {
        return "PerimeterCommand: "+perimeter + all;
    }


}
