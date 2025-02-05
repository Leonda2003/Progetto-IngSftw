package is.system.prompt.grammarCommand.list;

import is.system.prompt.grammarCommand.AbstractGrammarCommand;
import is.system.prompt.grammarCommand.terminal.TerminalGrammarCommand;

public abstract class ListGrammarCommand extends AbstractGrammarCommand {

    TerminalGrammarCommand ls;


    public TerminalGrammarCommand getLs() {
        return ls;
    }
}