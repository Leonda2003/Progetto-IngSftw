package is.system.prompt.grammarCommand.perimeter;

import is.system.prompt.grammarCommand.GrammarCommand;
import is.system.prompt.grammarCommand.terminal.TerminalGrammarCommand;

public abstract class PerimeterGrammarCommand implements GrammarCommand {

    TerminalGrammarCommand perimeter;


    public TerminalGrammarCommand getPerimeter() {
        return perimeter;
    }
}
