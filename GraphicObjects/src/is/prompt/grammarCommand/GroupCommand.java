package is.prompt.grammarCommand;

import is.prompt.grammarCommand.terminal.TerminalCommand;
import is.prompt.visitor.Visitor;

import java.lang.reflect.InvocationTargetException;

public class GroupCommand extends AbstractCommand{


    private TerminalCommand grp;
    private ListIDCommand listIDCommand;

    public GroupCommand(TerminalCommand grp, ListIDCommand listIDCommand){
        this.grp = grp;
        this.listIDCommand = listIDCommand;
    }

    public void accept(Visitor v) throws InvocationTargetException, InstantiationException, IllegalAccessException {
        v.interpret(this);
    }

    public String toString() {
        return "GroupCommand: "+ grp + listIDCommand;
    }

    public TerminalCommand getGrp() {
        return grp;
    }

    public ListIDCommand getListIDCommand() {
        return listIDCommand;
    }
}
