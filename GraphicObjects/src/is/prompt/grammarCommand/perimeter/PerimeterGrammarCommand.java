package is.prompt.grammarCommand.perimeter;

import is.prompt.grammarCommand.AbstractGrammarCommand;
import is.prompt.grammarCommand.terminal.TerminalGrammarCommand;

public abstract class PerimeterGrammarCommand extends AbstractGrammarCommand {

    TerminalGrammarCommand perimeter;


    public TerminalGrammarCommand getPerimeter() {
        return perimeter;
    }
}
