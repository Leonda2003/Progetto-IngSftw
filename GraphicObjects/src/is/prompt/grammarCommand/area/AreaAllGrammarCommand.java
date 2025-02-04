package is.prompt.grammarCommand.area;

import is.prompt.grammarCommand.terminal.TerminalGrammarCommand;
import is.prompt.visitor.Visitor;

import java.lang.reflect.InvocationTargetException;

public class AreaAllGrammarCommand extends AreaGrammarCommand {

    TerminalGrammarCommand all;

    public AreaAllGrammarCommand(TerminalGrammarCommand area, TerminalGrammarCommand all) {
        this.area = area;
        this.all = all;
    }

    @Override
    public String toString() {
        return "AreaCommand: "+ area + all;
    }


    @Override
    public void accept(Visitor v) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        v.interpret(this);
    }

    public TerminalGrammarCommand getAll() {
        return all;
    }
}
