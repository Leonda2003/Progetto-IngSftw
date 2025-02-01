package is.shapes.specificCmd;

import is.cmd.Cmd;
import is.prompt.visitor.Context;
import is.shapes.model.GraphicObject;

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
		Context.CONTEXT.write("zommed");
		return true;
	}

	@Override
	public boolean undoIt() {
		object.scale(1.0 / factor);
		Context.CONTEXT.write("unzommed");
		return true;
	}

}
