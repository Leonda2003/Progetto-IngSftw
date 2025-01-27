package is.shapes.specificCmd;

import is.cmd.Cmd;
import is.shapes.model.GraphicObject;
import is.shapes.view.GraphicObjectPanel;

import java.awt.geom.Point2D;

public class RemoveObjectCmd implements Cmd {
    private final GraphicObjectPanel panel;
    private final GraphicObject go;

    private final Point2D oldPos;

    public RemoveObjectCmd(GraphicObjectPanel panel, GraphicObject go) {
        this.panel = panel;
        this.go = go;
        oldPos = go.getPosition();
    }

    @Override
    public boolean undoIt() {
        go.moveTo(oldPos);
        panel.add(go);
        return true;
    }

    @Override
    public boolean doIt() {
        panel.remove(go);
        return true;
    }

}
