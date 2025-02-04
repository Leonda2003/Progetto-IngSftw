package is.prompt.grammarCommand.typeconstr;

import is.prompt.grammarCommand.AbstractGrammarCommand;
import is.prompt.grammarCommand.type.TypeGrammarCommand;

public abstract class TypeconstrGrammarCommand extends AbstractGrammarCommand {

    TypeGrammarCommand shape;

    public TypeGrammarCommand getShape() {
        return shape;
    }
}
