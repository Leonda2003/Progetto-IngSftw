package is.shapes.specificCmd;

import is.cmd.Cmd;
import is.prompt.visitor.Context;
import is.shapes.model.GraphicObject;

import java.awt.geom.Point2D;

public class MoveCmd implements Cmd {

	private  final Point2D oldPos;

	private  final Point2D newPos;

	private  final GraphicObject object;
	
	public MoveCmd(GraphicObject go, Point2D pos) {
		oldPos = go.getPosition();
		newPos = pos;
		this.object = go;
	}

	@Override
	public boolean doIt() {
		object.moveTo(newPos);
		Context.CONTEXT.clearLine();
		return true;
	}

	@Override
	public boolean undoIt() {
		object.moveTo(oldPos);
		Context.CONTEXT.clearLine();
		return true;
	}

}
