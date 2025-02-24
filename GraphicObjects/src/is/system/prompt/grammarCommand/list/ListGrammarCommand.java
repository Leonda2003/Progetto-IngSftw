package is.system.prompt.grammarCommand.list;

import is.system.prompt.grammarCommand.GrammarCommand;
import is.system.prompt.grammarCommand.terminal.TerminalGrammarCommand;

public abstract class ListGrammarCommand implements GrammarCommand {

    TerminalGrammarCommand ls;


    public TerminalGrammarCommand getLs() {
        return ls;
    }
}