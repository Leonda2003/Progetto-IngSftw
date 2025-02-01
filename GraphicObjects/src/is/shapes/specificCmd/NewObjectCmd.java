package is.shapes.specificCmd;

import is.cmd.Cmd;
import is.shapes.model.GraphicObject;
import is.shapes.view.GraphicObjectPanel;
import is.prompt.visitor.Context;

public class NewObjectCmd implements Cmd {

	private final GraphicObjectPanel panel;
	private final GraphicObject go;

	public NewObjectCmd(GraphicObjectPanel panel, GraphicObject go) {
		this.panel = panel;
		this.go = go;
	}

	@Override
	public boolean doIt() {
		Context.CONTEXT.addGrapichObject(go);
		panel.add(go);
		return true;
	}

	@Override
	public boolean undoIt() {
		Context.CONTEXT.removeLastAdded ();
		panel.remove(go);
		return true;
	}

}
