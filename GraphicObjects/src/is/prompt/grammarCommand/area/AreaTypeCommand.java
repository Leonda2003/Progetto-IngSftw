package is.prompt.grammarCommand.area;

import is.prompt.grammarCommand.terminal.TerminalCommand;
import is.prompt.grammarCommand.type.TypeCommand;
import is.prompt.visitor.Visitor;

import java.lang.reflect.InvocationTargetException;

public class AreaTypeCommand extends AreaCommand{


    TypeCommand type;

    public AreaTypeCommand(TerminalCommand area, TypeCommand type) {
        this.area = area;
        this.type = type;
    }

    @Override
    public String toString() {
        return "AreaCommand: "+ area + type;
    }


    @Override
    public void accept(Visitor v) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        v.interpret(this);
    }

    public TypeCommand getType() {
        return type;
    }
}
