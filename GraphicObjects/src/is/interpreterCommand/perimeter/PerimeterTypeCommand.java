package is.interpreterCommand.perimeter;

import is.interpreterCommand.terminal.TerminalCommand;
import is.interpreterCommand.type.TypeCommand;
import is.visitor.Visitor;

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


}
