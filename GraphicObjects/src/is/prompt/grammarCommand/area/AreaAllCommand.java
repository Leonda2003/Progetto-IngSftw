package is.prompt.grammarCommand.area;

import is.prompt.grammarCommand.terminal.TerminalCommand;
import is.prompt.visitor.Visitor;

import java.lang.reflect.InvocationTargetException;

public class AreaAllCommand extends AreaCommand{

    TerminalCommand all;

    public AreaAllCommand(TerminalCommand area, TerminalCommand all) {
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

    public TerminalCommand getAll() {
        return all;
    }
}
