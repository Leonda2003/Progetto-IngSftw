package is.system.prompt.grammarCommand;

import is.system.prompt.grammarCommand.terminal.ObjID;
import is.system.prompt.visitor.Visitor;

import java.util.*;

public class ListIDGrammarCommand implements GrammarCommand {

    List<ObjID> listObjID= new LinkedList<>();

    public ListIDGrammarCommand(ObjID objID){
        listObjID.add(objID);
    }

    public void addObjectID(ObjID objID){
        listObjID.add(objID);
    }

    public void accept(Visitor v){
        v.interpret(this);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        Iterator<ObjID> it = listObjID .iterator();

        sb.append(it.next());
        while (it.hasNext()) {
            sb.append(", ");
            sb.append(it.next());
        }

        return sb.toString();

    }

    public List<ObjID> getListObjID() {
        return listObjID;
    }
}
