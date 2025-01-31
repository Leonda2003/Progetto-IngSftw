package is.shapes.specificCmd;

import is.cmd.Cmd;
import is.prompt.visitor.Context;
import is.shapes.model.GraphicObject;
import is.shapes.view.GraphicObjectPanel;

import java.util.List;

public class GroupCmd implements Cmd {


    private final GraphicObjectPanel panel;
    private final GraphicObject go;

    public GroupCmd(GraphicObjectPanel panel, GraphicObject go) {
        this.panel = panel;
        this.go = go;
    }

    @Override
    public boolean doIt() {
        Context.CONTEXT.addGrapichObject(go);
        //panel.add(go);
        return true;
    }


    @Override
    public boolean undoIt() {
        Context.CONTEXT.removeLastAddedGroup();
        panel.remove(go);
        return true;
    }
}
