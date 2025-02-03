package is.shapes.specificCmd;

import is.cmd.Cmd;
import is.prompt.visitor.Context;
import is.shapes.model.GraphicObject;
import is.shapes.model.GroupObject;
import is.shapes.view.GraphicObjectPanel;

public class UngroupCmd implements Cmd {

    private final GroupObject go;
    private final String id;
    private final GraphicObjectPanel panel;



    public UngroupCmd(GroupObject go, String id,GraphicObjectPanel graphicObjectPanel) {
        this.id = id;
        this.go = go;
        this.panel = graphicObjectPanel;
    }

    @Override
    public boolean doIt() {
        Context.CONTEXT.removeTheGroup(id,go);
        panel.remove(go);
        return true;
    }

    @Override
    public boolean undoIt() {
        Context.CONTEXT.addRemovedGroup(id,go);
        panel.add(go);
        return true;
    }
}
