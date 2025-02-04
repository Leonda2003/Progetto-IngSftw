package is.prompt.grammarCommand.list;

import is.prompt.grammarCommand.AbstractGrammarCommand;
import is.prompt.grammarCommand.terminal.TerminalGrammarCommand;

public abstract class ListGrammarCommand extends AbstractGrammarCommand {

    TerminalGrammarCommand ls;


    public TerminalGrammarCommand getLs() {
        return ls;
    }
}