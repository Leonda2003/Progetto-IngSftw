package is.system.shapes.specificCmd;

import is.system.cmd.Cmd;
import is.system.prompt.visitor.Context;
import is.system.shapes.model.GroupObject;
import is.system.shapes.view.GraphicObjectPanel;

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
        panel.add(go);
        return true;
    }


    @Override
    public boolean undoIt() {
        Context.CONTEXT.removeLastAddedGroup(go);
        panel.remove(go);
        return true;
    }

    @Override
    public String toString() {
        return "group command "+go.toString()+" "+go.getGroup();
    }
}
