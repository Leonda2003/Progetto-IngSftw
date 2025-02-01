package is.prompt.grammarCommand.perimeter;

import is.prompt.grammarCommand.terminal.TerminalCommand;
import is.prompt.grammarCommand.type.TypeCommand;
import is.prompt.visitor.Visitor;

import java.lang.reflect.InvocationTargetException;

public class PerimeterTypeCommand extends PerimeterCommand{

    TypeCommand type;

    public PerimeterTypeCommand(TerminalCommand perimeter, TypeCommand type) {
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

    public TypeCommand getType() {
        return type;
    }
}
