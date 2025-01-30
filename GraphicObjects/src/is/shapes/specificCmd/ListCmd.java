package is.shapes.specificCmd;

import is.prompt.parser.analyzer.Token;
import is.cmd.Cmd;
import is.shapes.model.GraphicObject;
import is.prompt.GraphicObjectCommandPrompt;
import is.prompt.visitor.Context;

import java.util.HashMap;

public class ListCmd implements Cmd {

    ;
    private final String id;

    private final GraphicObjectCommandPrompt prompt;

    private final Token token;

    public ListCmd(String id,Token t, GraphicObjectCommandPrompt prompt){
        this.id = id;
        this.token = t;
        this.prompt = prompt;
    }

    @Override
    public boolean doIt() {
        HashMap<String,GraphicObject> objectHashMap=new HashMap<>();
        StringBuilder sb = new StringBuilder();
        switch (token){
            case OBJ_ID:
                prompt.write(Context.CONTEXT.getGraphicObject(id).properties(id));
                return false;
            case CIRCLE:
                objectHashMap=Context.CONTEXT.getType("Circle");
                sb.append("CIRCLE OBJECT:\n");
                break;
            case RECTANGLE:
                objectHashMap=Context.CONTEXT.getType("Rectangle");
                sb.append("RECTANGLE OBJECT:\n");
                break;
            case IMG:
                objectHashMap=Context.CONTEXT.getType("Image");
                sb.append("IMAGE OBJECT:\n");
                break;
            case GROUPS:
                objectHashMap=Context.CONTEXT.getType("Groups");
                sb.append("GROUPS OBJECT:\n");
                break;
            case ALL:
                objectHashMap=Context.CONTEXT.getType("All");
                sb.append("ALL OBJECT:\n");
                break;
        }
        for(String id : objectHashMap.keySet()){
            GraphicObject g = objectHashMap.get(id);
            sb.append(g.properties(id));
            sb.append("\n");
        }
        prompt.write(sb.toString());
        return false;
    }

    @Override
    public boolean undoIt() {
        return false;
    }
}
