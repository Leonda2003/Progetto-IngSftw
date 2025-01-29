package is.interpreterCommand.list;

import is.interpreterCommand.terminal.TerminalCommand;
import is.interpreterCommand.type.TypeCommand;
import is.visitor.Visitor;

import java.lang.reflect.InvocationTargetException;

public class ListTypeCommand extends ListCommand{

    TypeCommand typeCommand;

    public ListTypeCommand(TerminalCommand ls, TypeCommand typeCommand) {
        this.ls = ls;
        this.typeCommand = typeCommand;
    }

    @Override
    public String toString() {
        return "ListCommand: "+ ls + typeCommand;
    }


    public TypeCommand getTypeCommand() {
        return typeCommand;
    }

    @Override
    public void accept(Visitor v) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        v.interpret(this);
    }
}
