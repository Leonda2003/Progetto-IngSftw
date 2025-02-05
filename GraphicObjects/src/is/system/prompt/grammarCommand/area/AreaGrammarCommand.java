package is.system.prompt.grammarCommand.area;

import is.system.prompt.grammarCommand.AbstractGrammarCommand;
import is.system.prompt.grammarCommand.terminal.TerminalGrammarCommand;

public abstract class AreaGrammarCommand extends AbstractGrammarCommand {

    TerminalGrammarCommand area;

    public TerminalGrammarCommand getArea() {
        return area;
    }
}
