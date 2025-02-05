package is.system.prompt.grammarCommand;

import is.system.prompt.grammarCommand.terminal.TerminalGrammarCommand;
import is.system.prompt.visitor.Visitor;

import java.lang.reflect.InvocationTargetException;

public class GroupGrammarCommand extends AbstractGrammarCommand {


    private TerminalGrammarCommand grp;
    private ListIDGrammarCommand listIDCommand;

    public GroupGrammarCommand(TerminalGrammarCommand grp, ListIDGrammarCommand listIDCommand){
        this.grp = grp;
        this.listIDCommand = listIDCommand;
    }

    public void accept(Visitor v) throws InvocationTargetException, InstantiationException, IllegalAccessException {
        v.interpret(this);
    }

    public String toString() {
        return "GroupCommand: "+ grp + listIDCommand;
    }

    public TerminalGrammarCommand getGrp() {
        return grp;
    }

    public ListIDGrammarCommand getListIDCommand() {
        return listIDCommand;
    }
}
