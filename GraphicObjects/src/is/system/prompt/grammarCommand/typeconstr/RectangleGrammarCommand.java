package is.system.prompt.grammarCommand.typeconstr;

import is.system.prompt.grammarCommand.PosGrammarCommand;
import is.system.prompt.grammarCommand.type.TypeGrammarCommand;

public class RectangleGrammarCommand extends TypeconstrGrammarCommand {

    PosGrammarCommand pos;

    public RectangleGrammarCommand(TypeGrammarCommand rectangle, PosGrammarCommand pos) {
        this.shape = rectangle;
        this.pos = pos;
    }

    @Override
    public String toString() {
        return ""+ shape + pos+" ";
    }




    public PosGrammarCommand getPos() {
        return pos;
    }
}
