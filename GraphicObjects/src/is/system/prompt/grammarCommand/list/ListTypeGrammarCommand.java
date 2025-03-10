package is.system.prompt.grammarCommand.list;

import is.system.prompt.grammarCommand.terminal.TerminalGrammarCommand;
import is.system.prompt.grammarCommand.type.TypeGrammarCommand;
import is.system.prompt.visitor.Visitor;

import java.lang.reflect.InvocationTargetException;

public class ListTypeGrammarCommand extends ListGrammarCommand {

    TypeGrammarCommand typeCommand;

    public ListTypeGrammarCommand(TerminalGrammarCommand ls, TypeGrammarCommand typeCommand) {
        this.ls = ls;
        this.typeCommand = typeCommand;
    }

    @Override
    public String toString() {
        return "ListCommand: "+ ls + typeCommand;
    }


    public TypeGrammarCommand getTypeCommand() {
        return typeCommand;
    }

    @Override
    public void accept(Visitor v) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        v.interpret(this);
    }
}
