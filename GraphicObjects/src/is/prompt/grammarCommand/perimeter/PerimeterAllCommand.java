package is.prompt.grammarCommand.perimeter;

import is.prompt.grammarCommand.terminal.TerminalCommand;
import is.prompt.visitor.Visitor;

import java.lang.reflect.InvocationTargetException;

public class PerimeterAllCommand extends PerimeterCommand{

    TerminalCommand all;

    public PerimeterAllCommand(TerminalCommand perimeter, TerminalCommand all) {
        this.perimeter = perimeter;
        this.all = all;
    }

    public TerminalCommand getAll() {
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
