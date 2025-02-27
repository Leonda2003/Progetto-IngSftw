package is.system.shapes.specificCmd;

import is.system.cmd.Cmd;
import is.system.prompt.visitor.Context;
import is.system.shapes.model.GraphicObject;

public class ZoomCmd implements Cmd {
	
	private final GraphicObject object;
	private final double factor;

	public ZoomCmd(GraphicObject obj, double factor) {
		object = obj;
		this.factor = factor;
	}

	@Override
	public boolean doIt() {
		object.scale(factor);
		Context.CONTEXT.clearLine();
		return true;
	}

	@Override
	public boolean undoIt() {
		object.scale(1.0 / factor);
		Context.CONTEXT.clearLine();
		return true;
	}

}
