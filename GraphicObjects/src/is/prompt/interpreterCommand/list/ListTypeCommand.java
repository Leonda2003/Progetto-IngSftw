package is.prompt.interpreterCommand.list;

import is.prompt.interpreterCommand.terminal.TerminalCommand;
import is.prompt.interpreterCommand.type.TypeCommand;
import is.prompt.visitor.Visitor;

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
