package is.system.shapes.specificCmd;

import is.system.cmd.Cmd;
import is.system.prompt.visitor.Context;
import is.system.shapes.model.GraphicObject;
import is.system.shapes.model.GroupObject;

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

		if(go instanceof GroupObject){
			GroupObject group = (GroupObject) go;
			addMemberOldPosition(group);
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
		if(object instanceof GroupObject) undoItForGroup((GroupObject) object);
		else object.moveTo(oldPos);
		Context.CONTEXT.clearLine();
		return true;
	}


	private void undoItForGroup(GroupObject group) {
		HashMap<String,GraphicObject> objectMap=group.getGroup();
		for(String id : objectMap.keySet()){
			GraphicObject g = objectMap.get(id);
			if(g instanceof GroupObject) undoItForGroup((GroupObject) g);
			else g.moveTo(memberOldPos.get(id));
		}
	}

	private void addMemberOldPosition(GroupObject group){
		HashMap<String,GraphicObject> objectMap=group.getGroup();
		for(String id : objectMap.keySet()){
			GraphicObject g = objectMap.get(id);
			if(g instanceof GroupObject) addMemberOldPosition((GroupObject) g);
			else memberOldPos.put(id,g.getPosition());
		}
	}

	@Override
	public String toString() {
		return "move command "+object.toString()+"new pos: "+newPos+" old pos: "+oldPos;
	}


}
