package is.shapes.specificCmd;

import is.cmd.Cmd;
import is.shapes.model.GraphicObject;
import is.shapes.view.GraphicObjectPanel;
import is.visitor.Context;

import java.awt.geom.Point2D;

public class RemoveObjectCmd implements Cmd {
    private final GraphicObjectPanel panel;
    private final String id;
    private final GraphicObject go;

    public RemoveObjectCmd(GraphicObjectPanel panel, String id) {
        this.panel = panel;
        this.id = id;
        go = Context.CONTEXT.getGraphicObject(id);
    }

    @Override
    public boolean undoIt() {
        Context.CONTEXT.addRemoved(id,go);
        panel.add(go);
        return true;
    }

    @Override
    public boolean doIt() {
        GraphicObject go =  Context.CONTEXT.remove(id);
        panel.remove(go);
        return true;
    }

}
