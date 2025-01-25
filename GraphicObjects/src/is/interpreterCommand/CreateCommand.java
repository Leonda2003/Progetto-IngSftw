package is.interpreterCommand;

import is.interpreterCommand.terminal.TerminalCommand;
import is.interpreterCommand.typeconstr.TypeconstrCommand;
import is.visitor.Visitor;

public class CreateCommand extends AbstractCommand{

    private TerminalCommand NEW;
    private TypeconstrCommand typeconstr;
    private PosCommand pos;

    CreateCommand(TerminalCommand NEW,TypeconstrCommand typeconstr, PosCommand pos){
        this.NEW = NEW;
        this.typeconstr = typeconstr;
        this.pos = pos;
    }

    public void accept(Visitor v){
        v.interpret(this);
    }


}
