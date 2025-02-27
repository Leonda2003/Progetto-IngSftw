package is.system.shapes.specificCmd;

import is.system.cmd.Cmd;
import is.system.prompt.visitor.Context;
import is.system.shapes.model.GroupObject;
import is.system.shapes.view.GraphicObjectPanel;

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
