package is.parser;

import is.analyzer.Context;
import is.interpreterCommand.Command;

public class ConcreteFactoryParser extends FactoryParser{

    private Context c;
    ConcreteFactoryParser(Context c) {
        this.c = c;
    }
    @Override
    public Command createCommandInterpreter() {
        return null;
    }
}
