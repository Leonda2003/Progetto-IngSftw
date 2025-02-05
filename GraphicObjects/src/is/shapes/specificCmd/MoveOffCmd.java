package is.shapes.specificCmd;

import is.cmd.Cmd;
import is.prompt.visitor.Context;
import is.shapes.model.GraphicObject;
import is.shapes.model.GroupObject;

import java.awt.geom.Point2D;
import java.util.HashMap;

public class MoveOffCmd implements Cmd {

    private  final Point2D oldPos;

    private  final Point2D newPos;

    private  final GraphicObject object;

    private final HashMap<String,Point2D> memberOldPos=new HashMap<>();

    public MoveOffCmd(GraphicObject go, Point2D pos) {

        oldPos = go.getPosition();
        newPos = pos;
        this.object = go;

        if(go.getType().equals("Group")){
            GroupObject group = (GroupObject) go;
            HashMap<String,GraphicObject> objectMap=group.getGroup();
            for(String id : objectMap.keySet()) memberOldPos.put(id,objectMap.get(id).getPosition());
        }
    }

    @Override
    public boolean doIt() {
        if(object.getType().equals("Group")) return doItForGroup();
        object.moveTo(new Point2D.Double(object.getPosition().getX()+ newPos.getX(),object.getPosition().getY()+newPos.getY()));
        Context.CONTEXT.clearLine();
        return true;
    }

    @Override
    public boolean undoIt() {
        if(object.getType().equals("Group")) return undoItForGroup();
        object.moveTo(oldPos);
        Context.CONTEXT.clearLine();
        return true;
    }


    private boolean undoItForGroup() {
        GroupObject group = (GroupObject) object;
        HashMap<String,GraphicObject> objectMap=group.getGroup();
        for(String id : objectMap.keySet()){
            GraphicObject g = objectMap.get(id);
            g.moveTo(memberOldPos.get(id));
        }
        Context.CONTEXT.clearLine();
        return true;
    }

    private boolean doItForGroup() {
        GroupObject group = (GroupObject) object;
        HashMap<String,GraphicObject> objectMap=group.getGroup();
        for(String id : objectMap.keySet()){
            GraphicObject g = objectMap.get(id);
            g.moveTo(new Point2D.Double(g.getPosition().getX()+ newPos.getX(),g.getPosition().getY()+newPos.getY()));
        }
        Context.CONTEXT.clearLine();
        return true;
    }
}
