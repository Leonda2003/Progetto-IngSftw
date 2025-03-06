package is.system.prompt.parser;

import is.system.prompt.grammarCommand.GrammarCommand;

import java.io.StringReader;

public interface BuilderParser {

    GrammarCommand getCommandToInterpret(String command);
}
