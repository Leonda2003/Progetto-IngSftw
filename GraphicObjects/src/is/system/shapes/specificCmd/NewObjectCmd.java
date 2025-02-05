package is.system.shapes.specificCmd;

import is.system.exception.cmd.Cmd;
import is.system.shapes.model.GraphicObject;
import is.system.shapes.view.GraphicObjectPanel;
import is.system.prompt.visitor.Context;

public class NewObjectCmd implements Cmd {

	private final GraphicObjectPanel panel;
	private final GraphicObject go;

	public NewObjectCmd(GraphicObjectPanel panel, GraphicObject go) {
		this.panel = panel;
		this.go = go;
	}

	@Override
	public boolean doIt() {
		Context.CONTEXT.addGraphicObject(go);
		panel.add(go);
		return true;
	}

	@Override
	public boolean undoIt() {
		Context.CONTEXT.removeLastAdded();
		panel.remove(go);
		return true;
	}

}
