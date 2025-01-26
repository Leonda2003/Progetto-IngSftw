package is.interpreterCommand;

import is.visitor.Visitor;

public interface Command {
    void accept(Visitor v) throws NoSuchMethodException;
}
