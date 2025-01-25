package is.interpreterCommand.perimeter;

import is.interpreterCommand.AbstractCommand;
import is.interpreterCommand.terminal.TerminalCommand;
import is.visitor.Visitor;

public abstract class PerimeterCommand extends AbstractCommand{

    TerminalCommand perimeter;

    public void accept(Visitor v){
        v.interpret(this);
    }

}
