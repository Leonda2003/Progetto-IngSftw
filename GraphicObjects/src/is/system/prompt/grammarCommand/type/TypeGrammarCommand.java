package is.system.prompt.grammarCommand.type;

import is.system.prompt.grammarCommand.GrammarCommand;
import is.system.prompt.parser.analyzer.Token;
import is.system.prompt.visitor.Visitor;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.reflect.InvocationTargetException;

public abstract class TypeGrammarCommand implements GrammarCommand {

    Token shape;

    TypeGrammarCommand(Token token){
        this.shape = token;
    }

    @Override
    public String toString() {
        return shape.toString()+" ";
    }

    public Token getToken() {
        return shape;
    }

    public void accept(Visitor v) throws InvocationTargetException, InstantiationException, IllegalAccessException {
        v.interpret(this);
    }
}
