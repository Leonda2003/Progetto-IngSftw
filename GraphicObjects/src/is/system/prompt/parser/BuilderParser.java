package is.system.prompt.parser;

import is.system.prompt.grammarCommand.GrammarCommand;

public interface BuilderParser {

    GrammarCommand getCommandToInterpret();
}
