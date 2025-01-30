package is.prompt.grammarCommand;

import is.prompt.grammarCommand.terminal.TerminalCommand;
import is.prompt.visitor.Visitor;

public class GroupCommand extends AbstractCommand{


    private TerminalCommand grp;
    private ListIDCommand listIDCommand;

    public GroupCommand(TerminalCommand grp, ListIDCommand listIDCommand){
        this.grp = grp;
        this.listIDCommand = listIDCommand;
    }

    public void accept(Visitor v){
        v.interpret(this);
    }

    public String toString() {
        return "GroupCommand: "+ grp + listIDCommand;
    }
}
