package is.prompt.grammarCommand;

import is.prompt.grammarCommand.terminal.ObjID;
import is.prompt.visitor.Visitor;

import java.util.*;

public class ListIDCommand extends AbstractCommand{

    List<ObjID> listObjID= new LinkedList<>();

    public ListIDCommand(ObjID objID){
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
