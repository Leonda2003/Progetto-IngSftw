package is.system.prompt.grammarCommand;

import is.system.prompt.grammarCommand.terminal.TerminalGrammarCommand;
import is.system.prompt.grammarCommand.typeconstr.TypeconstrGrammarCommand;
import is.system.prompt.visitor.Visitor;

import java.lang.reflect.InvocationTargetException;

public class CreateGrammarCommand implements GrammarCommand {

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
