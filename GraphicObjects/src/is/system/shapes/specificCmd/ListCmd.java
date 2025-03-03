package is.system.shapes.specificCmd;

import is.system.prompt.parser.analyzer.Token;
import is.system.cmd.Cmd;
import is.system.shapes.model.GraphicObject;
import is.system.prompt.visitor.Context;

import java.util.HashMap;

public class ListCmd implements Cmd {

    ;
    private final String id;

    private final Token token;

    public ListCmd(String id,Token t){
        this.id = id;
        this.token = t;
    }

    @Override
    public boolean doIt() {
        HashMap<String,GraphicObject> objectHashMap=new HashMap<>();
        StringBuilder sb = new StringBuilder();
        switch (token){
            case OBJ_ID:
                Context.CONTEXT.write(Context.CONTEXT.getGraphicObject(id).properties(id));
                return false;
            case CIRCLE:
                objectHashMap=Context.CONTEXT.getType("Circle");
                sb.append("CIRCLE OBJECT:");
                break;
            case RECTANGLE:
                objectHashMap=Context.CONTEXT.getType("Rectangle");
                sb.append("RECTANGLE OBJECT:");
                break;
            case IMG:
                objectHashMap=Context.CONTEXT.getType("Image");
                sb.append("IMAGE OBJECT:");
                break;
            case GROUPS:
                objectHashMap=Context.CONTEXT.getType("Group");
                sb.append("GROUPS OBJECT:");
                break;
            case ALL:
                objectHashMap=Context.CONTEXT.getType("All");
                sb.append("ALL OBJECT:");
                break;
        }

        for(String id : objectHashMap.keySet()){
            sb.append("\n");
            GraphicObject g = objectHashMap.get(id);
            sb.append("   "+g.properties(id));
        }
        Context.CONTEXT.write(sb.toString());
        return false;
    }

    @Override
    public boolean undoIt() {
        return false;
    }

    @Override
    public String toString() {
        return "list command "+" id "+id+" token "+token;
    }
}
