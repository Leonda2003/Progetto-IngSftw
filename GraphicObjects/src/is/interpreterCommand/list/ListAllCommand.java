package is.interpreterCommand.list;

import is.interpreterCommand.terminal.All_Groups;
import is.interpreterCommand.terminal.TerminalCommand;
import is.visitor.Visitor;

import java.lang.reflect.InvocationTargetException;

public class ListAllCommand extends ListCommand{
    All_Groups all;

    public ListAllCommand(TerminalCommand ls, All_Groups all) {
        this.ls = ls;
        this.all = all;
    }

    @Override
    public String toString() {
        return "ListCommand: "+ ls + all;
    }


    public All_Groups getAll() {
        return all;
    }

    @Override
    public void accept(Visitor v) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        v.interpret(this);
    }
}
