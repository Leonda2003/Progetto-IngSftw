package is.prompt.grammarCommand.area;

import is.prompt.grammarCommand.AbstractGrammarCommand;
import is.prompt.grammarCommand.terminal.TerminalGrammarCommand;

public abstract class AreaGrammarCommand extends AbstractGrammarCommand {

    TerminalGrammarCommand area;

    public TerminalGrammarCommand getArea() {
        return area;
    }
}
