package is.system.prompt.grammarCommand.perimeter;

import is.system.prompt.grammarCommand.AbstractGrammarCommand;
import is.system.prompt.grammarCommand.terminal.TerminalGrammarCommand;

public abstract class PerimeterGrammarCommand extends AbstractGrammarCommand {

    TerminalGrammarCommand perimeter;


    public TerminalGrammarCommand getPerimeter() {
        return perimeter;
    }
}
