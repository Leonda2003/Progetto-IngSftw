package is.interpreterCommand;

import is.interpreterCommand.terminal.TerminalCommand;
import is.visitor.Visitor;

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
}
