package is.prompt.grammarCommand.terminal;

import is.prompt.parser.analyzer.Token;

public class ObjID extends TerminalGrammarCommand {

    private String ID;
    public ObjID(Token token, String ID) {
        super(token);
        this.ID = ID;
    }

    @Override
    public String toString() {
        return ID+" ";
    }

    public String getID() {
        return ID;
    }
}
