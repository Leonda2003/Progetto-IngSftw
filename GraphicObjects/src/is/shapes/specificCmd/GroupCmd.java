package is.shapes.specificCmd;

import is.cmd.Cmd;
import is.prompt.visitor.Context;
import is.shapes.model.GraphicObject;
import is.shapes.model.GroupObject;
import is.shapes.view.GraphicObjectPanel;

import java.util.List;

public class GroupCmd implements Cmd {


    private final GraphicObjectPanel panel;
    private final GroupObject go;

    public GroupCmd(GraphicObjectPanel panel, GroupObject go) {
        this.panel = panel;
        this.go = go;
    }

    @Override
    public boolean doIt() {
        Context.CONTEXT.addGraphicObject(go);
        //panel.add(go);
        return true;
    }


    @Override
    public boolean undoIt() {
        Context.CONTEXT.removeLastAddedGroup(go);
        panel.remove(go);
        return true;
    }
}
