package is.system.prompt.grammarCommand.typeconstr;

import is.system.prompt.grammarCommand.AbstractGrammarCommand;
import is.system.prompt.grammarCommand.type.TypeGrammarCommand;

public abstract class TypeconstrGrammarCommand extends AbstractGrammarCommand {

    TypeGrammarCommand shape;

    public TypeGrammarCommand getShape() {
        return shape;
    }
}
