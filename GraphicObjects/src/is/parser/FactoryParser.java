package is.parser;

import is.analyzer.Context;
import is.interpreterCommand.Command;

public abstract class FactoryParser {

    public abstract Command createCommandToInterpret();
}
