package is.prompt.grammarCommand;

import is.prompt.grammarCommand.terminal.TerminalGrammarCommand;
import is.prompt.grammarCommand.typeconstr.TypeconstrGrammarCommand;
import is.prompt.visitor.Visitor;

import java.lang.reflect.InvocationTargetException;

public class CreateGrammarCommand extends AbstractGrammarCommand {

    private TerminalGrammarCommand NEW;
    private TypeconstrGrammarCommand typeconstr;
    private PosGrammarCommand pos;

    public CreateGrammarCommand(TerminalGrammarCommand NEW, TypeconstrGrammarCommand typeconstr, PosGrammarCommand pos){
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

    public TerminalGrammarCommand getNEW() {
        return NEW;
    }

    public TypeconstrGrammarCommand getTypeconstr() {
        return typeconstr;
    }

    public PosGrammarCommand getPos() {
        return pos;
    }
}
