package is.system.prompt.grammarCommand.area;

import is.system.prompt.grammarCommand.terminal.TerminalGrammarCommand;
import is.system.prompt.grammarCommand.type.TypeGrammarCommand;
import is.system.prompt.visitor.Visitor;

import java.lang.reflect.InvocationTargetException;

public class AreaTypeGrammarCommand extends AreaGrammarCommand {


    TypeGrammarCommand type;

    public AreaTypeGrammarCommand(TerminalGrammarCommand area, TypeGrammarCommand type) {
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

    public TypeGrammarCommand getType() {
        return type;
    }
}
