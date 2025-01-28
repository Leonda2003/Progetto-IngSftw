package is.interpreterCommand.perimeter;

import is.interpreterCommand.terminal.TerminalCommand;
import is.visitor.Visitor;

import java.lang.reflect.InvocationTargetException;

public class PerimeterAllCommand extends PerimeterCommand{

    TerminalCommand all;

    public PerimeterAllCommand(TerminalCommand perimeter, TerminalCommand all) {
        this.perimeter = perimeter;
        this.all = all;
    }

    @Override
    public String toString() {
        return "PerimeterCommand: "+perimeter + all;
    }


}
