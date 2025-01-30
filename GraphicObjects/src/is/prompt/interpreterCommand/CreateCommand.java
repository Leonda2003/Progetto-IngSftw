package is.prompt.interpreterCommand;

import is.prompt.interpreterCommand.terminal.TerminalCommand;
import is.prompt.interpreterCommand.typeconstr.TypeconstrCommand;
import is.prompt.visitor.Visitor;

import java.lang.reflect.InvocationTargetException;

public class CreateCommand extends AbstractCommand{

    private TerminalCommand NEW;
    private TypeconstrCommand typeconstr;
    private PosCommand pos;

    public CreateCommand(TerminalCommand NEW,TypeconstrCommand typeconstr, PosCommand pos){
        this.NEW = NEW;
        this.typeconstr = typeconstr;
        this.pos = pos;
    }

    public void accept(Visitor v) throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        v.interpret(this);
    }


    @Override
    public String toString() {
        return "CreateCommand: " +NEW + typeconstr + pos;
    }

    public TerminalCommand getNEW() {
        return NEW;
    }

    public TypeconstrCommand getTypeconstr() {
        return typeconstr;
    }

    public PosCommand getPos() {
        return pos;
    }
}
