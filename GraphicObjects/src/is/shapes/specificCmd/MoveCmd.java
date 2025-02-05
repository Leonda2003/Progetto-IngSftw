package is.shapes.specificCmd;

import is.cmd.Cmd;
import is.prompt.visitor.Context;
import is.shapes.model.GraphicObject;
import is.shapes.model.GroupObject;

import java.awt.geom.Point2D;
import java.util.HashMap;

public class MoveCmd implements Cmd {

	private  final Point2D oldPos;

	private  final Point2D newPos;

	private  final GraphicObject object;

	private final HashMap<String,Point2D> memberOldPos=new HashMap<>();
	
	public MoveCmd(GraphicObject go, Point2D pos) {

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
		object.moveTo(newPos);
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



}
