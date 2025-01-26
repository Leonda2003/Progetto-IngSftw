package is.interpreterCommand.terminal;

import is.analyzer.Token;

public class ObjID extends TerminalCommand{

    String ID;
    ObjID(Token token,String ID) {
        super(token);
        this.ID = ID;
    }
}
