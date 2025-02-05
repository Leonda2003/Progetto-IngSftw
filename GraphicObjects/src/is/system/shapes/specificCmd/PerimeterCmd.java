package is.system.shapes.specificCmd;

import is.system.exception.cmd.Cmd;
import is.system.prompt.parser.analyzer.Token;
import is.system.prompt.visitor.Context;
import is.system.shapes.model.GraphicObject;

import java.text.DecimalFormat;
import java.util.HashMap;

public class PerimeterCmd implements Cmd {

    private final String id;

    private final Token token;

    public PerimeterCmd(String id,Token t){
        this.id = id;
        this.token = t;
    }

    @Override
    public boolean doIt() {
        HashMap<String,GraphicObject> objectHashMap=new HashMap<>();
        StringBuilder sb = new StringBuilder();
        DecimalFormat df = new DecimalFormat("#.###");
        switch (token){
            case OBJ_ID:
                Context.CONTEXT.write("PERIMETER "+id+" = "+df.format(Context.CONTEXT.getGraphicObject(id).perimeter()));
                return false;
            case CIRCLE:
                objectHashMap=Context.CONTEXT.getType("Circle");
                sb.append("   CIRCLE PERIMETER =");
                break;
            case RECTANGLE:
                objectHashMap=Context.CONTEXT.getType("Rectangle");
                sb.append("   RECTANGLE PERIMETER =");
                break;
            case IMG:
                objectHashMap=Context.CONTEXT.getType("Image");
                sb.append("   IMAGE PERIMETER =");
                break;
            case ALL:
                objectHashMap=Context.CONTEXT.getAllShape();
                sb.append("   ALL SHAPE PERIMETER =");
                break;
        }
        double sum = 0;
        for(String id : objectHashMap.keySet()){
            GraphicObject g = objectHashMap.get(id);
            sum += g.perimeter();
        }
        sb.append(df.format(sum));
        Context.CONTEXT.write(sb.toString());
        return false;
    }

    @Override
    public boolean undoIt() {
        return false;
    }
}
