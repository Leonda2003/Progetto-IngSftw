package is.system.prompt.grammarCommand.area;


import is.system.prompt.grammarCommand.GrammarCommand;
import is.system.prompt.grammarCommand.terminal.TerminalGrammarCommand;

public abstract class AreaGrammarCommand implements GrammarCommand {

    TerminalGrammarCommand area;

    public TerminalGrammarCommand getArea() {
        return area;
    }
}
